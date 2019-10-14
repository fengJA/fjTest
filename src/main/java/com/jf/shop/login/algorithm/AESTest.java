package com.jf.shop.login.algorithm;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AESTest {
    private static final String ALGORITHM = "AES";

    //测试
    public static void main(String[] args) throws Exception {
        String keyValue = "12345678";//128对应是8位
        String msg = "我是你的爸爸";

        String encrypt = AESTest.encrypt(msg, keyValue);
        System.out.println("encrypt:"+encrypt);//encrypt:a+R58tUcrQCQQM7Ap8QmRuh2qsP2E+n1

        String decrypt = AESTest.decrypt(encrypt, keyValue);
        System.out.println("decrypt:"+decrypt);//decrypt:我是你的爸爸

        AESTest.aesEncode();
    }

    //加密
    public static String encrypt(String Data,String keyValue) throws Exception {
        Key key = generateKey(ALGORITHM,keyValue);
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    //解密
    public static String decrypt(String encryptedValue,String keyValue) throws Exception {
        Key key = generateKey(ALGORITHM,keyValue);
        byte[] decodeBytes = new BASE64Decoder().decodeBuffer(encryptedValue);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decode = cipher.doFinal(decodeBytes);
        return new String(decode);
    }

    //根据密钥和算法生成Key
    private static SecretKey generateKey(String algo,String keyValue) throws Exception {
        //用SecretFactory生成key会报错：java.security.NoSuchAlgorithmException: AES SecretKeyFactory not available
//        Key keys = new SecretKeySpec(keyValue.getBytes(), algo);
        KeyGenerator key = KeyGenerator.getInstance(algo);
        key.init(128,new SecureRandom(keyValue.getBytes()));//初始化秘钥生成器，必须是128, 192 or 256位
        SecretKey secretKey = key.generateKey();
        return secretKey;
    }

    //AES编码
    public static void aesEncode(){
        //keyValue只能是16个字节
        String keyValue = "12345678qwertyui";
        String msg = "我是你的爸爸";
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            Key desKeySpec = new SecretKeySpec(keyValue.getBytes(), ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, desKeySpec);

            byte[] decryptBytes = cipher.doFinal(msg.getBytes());
            System.out.println("decryptBytes:" + new String(decryptBytes));//decryptBytes:�AX��Nvs_��kߞe!T��r9O�Q��
            //因为decryptBytes中会出现乱码，所以用Base64编码
            byte[] encodes = Base64.getEncoder().encode(decryptBytes);
            System.out.println("encodes:"+new String(encodes));//encodes:qAxBWP7MCPsZTnZzX4L8a9+eZSEMVLnWcjlPkwNR+Z8=

            String encode = new BASE64Encoder().encode(decryptBytes);//encode:qAxBWP7MCPsZTnZzX4L8a9+eZSEMVLnWcjlPkwNR+Z8=
            System.out.println("encode:"+encode);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

}
