package com.jf.shop.login.algorithm;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.SecureRandom;

public class AESDemo {
    private static final String ALGORITHM = "AES";
    // 算法名称/加密模式/填充方式
    public static final String CIPHER_ALGORITHM_ECB = "DES/ECB/PKCS5Padding";
    public static final String CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5Padding";


    //测试
    public static void main(String[] args) throws Exception {
        String keyValue = "4E7FF1C12222222222156876148416548155555555555544654777";//没有位数限制？？
        String msg = "我是你的爸爸";
        String iv = "abcdefghijklmnop";

        String encrypt = AESDemo.encrypt(msg, keyValue,iv);
        System.out.println("encrypt:"+encrypt);//encrypt:TsxnbWcfWHLzOm0UZ7368AtoEDjn3IAzGgn2j1qbFV8=

        String decrypt = AESDemo.decrypt(encrypt, keyValue,iv);
        System.out.println("decrypt:"+decrypt);//decrypt:我是你的爸爸
    }

    //加密
    public static String encrypt(String Data,String keyValue,String iv) throws Exception {
        Key key = generateKey(ALGORITHM,keyValue);
        Cipher c = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
        c.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(iv.getBytes()));
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    //解密
    public static String decrypt(String encryptedValue,String keyValue,String iv) throws Exception {
        Key key = generateKey(ALGORITHM,keyValue);
        byte[] decodeBytes = new BASE64Decoder().decodeBuffer(encryptedValue);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
        cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(iv.getBytes()));
        byte[] decode = cipher.doFinal(decodeBytes);
        return new String(decode);
    }

    //根据密钥和算法生成Key
    private static SecretKey generateKey(String algo, String keyValue) throws Exception {
        KeyGenerator key = KeyGenerator.getInstance(algo);
        key.init(128,new SecureRandom(keyValue.getBytes()));//初始化秘钥生成器，必须是128, 192 or 256位
        SecretKey secretKey = key.generateKey();
        return secretKey;
    }
}
