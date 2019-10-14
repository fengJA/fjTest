package com.jf.shop.login.algorithm;

import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class DESTest {
    //加密算是是des
    private static final String ALGORITHM = "DES";
    //转换格式
    private static final String TRANSFORMATION = "DES/ECB/PKCS5Padding";

    public static void main(String[] args) throws Exception {
        //DES中的key只能是8位
        String key = "12345678";
        String msg = "我是你的爸爸";
        byte[] encryp = DESTest.encryp(msg.getBytes(), key.getBytes());
        System.out.println("encryp:"+new String(encryp));//encryp:HpZV8BQnhGDSw0oV4nEXBF4ygfyWd2ou
        byte[] decrypt = DESTest.decrypt(encryp, key.getBytes());
        System.out.println("decrypt:"+new String(decrypt));//decrypt:我是你的爸爸

        String encrypt = DESTest.encrypt(msg, key);
        System.out.println("encrypt:"+new String(encrypt));
    }

    //利用8个字节64位的key给src加密(DES一般是64位（字节）加密，其中有8位是秘钥(形参key))
    public static byte[] encryp(byte[] src,byte[] key){
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);

            //创建一个 DESKeySpec 对象，使用 key 中的前 8 个字节作为 DES 密钥的密钥内容。
            KeySpec keySpec = new DESKeySpec(key);
            SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryBytes = cipher.doFinal(src);
            System.out.println("encryBytes:"+new String(encryBytes));//encryBytes:�U�'�`��J�q^2���wj.

            //因为encryBytes中会出现乱码，所以用Base64编码
            byte[] encode = Base64.getEncoder().encode(encryBytes);

            System.out.println("encode:"+new String(encode));//encode:HpZV8BQnhGDSw0oV4nEXBF4ygfyWd2ou

            return encode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] decrypt(byte[] encryBytes,byte[] key){
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKeyFactory desecretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            KeySpec desKeySpec = new DESKeySpec(key);
            SecretKey desecretKey = desecretKeyFactory.generateSecret(desKeySpec);
            cipher.init(Cipher.DECRYPT_MODE, desecretKey);

            byte[] decode = Base64.getDecoder().decode(encryBytes);
            System.out.println("decode"+new String(decode));//decode�U�'�`��J�q^2���wj.
            byte[] decryptBytes = cipher.doFinal(decode);
            System.out.println("decryptBytes:" + new String(decryptBytes));//decryptBytes:我是你的爸爸

            return decryptBytes;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String encrypt(String Data,String keyValue) throws Exception {
        Key key = new SecretKeySpec(keyValue.getBytes(), ALGORITHM);
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key,new SecureRandom());
        byte[] encVal = c.doFinal(Data.getBytes());
        System.out.println("encVal:"+new String(encVal));
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }
}
