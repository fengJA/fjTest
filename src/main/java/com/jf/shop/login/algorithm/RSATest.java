package com.jf.shop.login.algorithm;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class RSATest {

    private static final int KEY_SIZE = 1024;//密钥长度 于原文长度对应 以及越长速度越慢

    private static Map<Integer,String> keyMap = new HashMap<>();//用于封装随机产生的公钥与私钥

    //随机生成密钥对
    public static void genKeyPair(){
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
            keyPairGenerator.initialize(KEY_SIZE,new SecureRandom()); // 初始化密钥对生成器
            KeyPair keyPair = keyPairGenerator.generateKeyPair(); // 生成一个密钥对，保存在keyPair中
            //得到公钥和私钥
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            //得到私钥和公钥字符窜
            String privateKeyEncode = Base64.getEncoder().encodeToString(privateKey.getEncoded());
            String publicKeyEncode = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            //0放公钥，1放私钥
            keyMap.put(0, publicKeyEncode);
            keyMap.put(1, privateKeyEncode);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    //公钥加密
    public static String encrypt(String str, String publicKey){
        try {
            byte[] decode = Base64.getDecoder().decode(publicKey.getBytes());//base64编码的公钥
            RSAPublicKey pubkey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decode));
            //RSA加密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubkey);
            String outStr = Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes("UTF-8")));
            return outStr;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    //RSA私钥解密
    public static String decrypt(String str, String privateKey){
        RSAPrivateKey priKey = null;
        try {
            //64位解码加密后的字符串
            byte[] inputByte = Base64.getDecoder().decode(str);
            //base64编码的私钥
            byte[] decoded = Base64.getDecoder().decode(privateKey);
            priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
            //RSA解密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            String outStr = new String(cipher.doFinal(inputByte));
            return outStr;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        long temp = System.currentTimeMillis();
        //生成公钥和私钥
        genKeyPair();
        //加密字符串
        //公钥:MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCYHEjHgkzs9ByXjuqcby4zfjvwx0glvW0KsgMvnIXqkENy487jdGxI32rk9yV7UQcFL0u2p4NtpZHCvPdBVnkZ9cXzWEny5msH1WvK1wYx/DwZMxTpp
        // eS1lPhmg528AJLvYQRbacaDz0s0oRxTTW3Fi/nFgM0AUrW2yWZ2qaSKSQIDAQAB
        System.out.println("公钥:" + keyMap.get(0));
        //私钥:MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJgcSMeCTOz0HJeO6pxvLjN+O/DHSCW9bQqyAy+cheqQQ3LjzuN0bEjfauT3JXtRBwUvS7ang22lkcK890FWeRn1xfNYSfLmawfVa8rXBj
        // H8PBkzFOml5LWU+GaDnbwAku9hBFtpxoPPSzShHFNNbcWL+cWAzQBStbbJZnappIpJAgMBAAECgYEAh7s1dlHfMtHhcZwl3dGX+Hr8Ia6xwnt1gcW28hVJqjVw+mQtiOmqdpxmVknzOEblbMr3VNXFgWHs30ifm
        // b3/8LhCW6HlWxFcTCgrbp4qfQrchUl9aBz1vqESN5V0XvVABaIBEXP724tqr9NJq5IGmFpF+FnWtwW7vas9gvEkyMECQQDax5ciBCG8vMY/egW0OjltjBfCMsplUJvUIMc3qIBupxY+UgjzosWVjyDMrZ7nc1bs+Z
        // BbHx3cvuikJiN0R/PvAkEAsf0VGgq3N93lHKuPKY0GZu6xE9tkIJRCjrBuccw9R2buxPPLnEDzxmt7JjLlBpKn6S5+RVeFLoKXn351mr9NRwJAJ3mV4nReZgClwLRSrSdIT2TUsJybelZmF4tmhrtnmQ+a6tnAdb1
        // r60VtBKFbJ6XfDFknW9mseLUVovCiuwt0jwJAdHz6royM1Rd4t4bgr4UIx504b5KKuXfPB6wY3ImbIXwQkP4uKBmqchYuBxYQJnkdQ9OV+LQLCiGWMmCFf73sSQJAE4UmKLd/pIKVXeDZNcBmOXFV4rpTftqC75tUJ
        // 5/sYGROf7zS8ecy2ETZMmfEyapiJOcED/bLPJWL9alxvrHFZA==
        System.out.println("私钥:" + keyMap.get(1));
        //生成密钥消耗时间:0.345秒
        System.out.println("生成密钥消耗时间:" + (System.currentTimeMillis() - temp) / 1000.0 + "秒");
        String message = "RSA测试ABCD~!@#$";
        System.out.println("原文:" + message);
        temp = System.currentTimeMillis();
        String messageEn = encrypt(message, keyMap.get(0));
        //密文:XeQFboVjWizFU96NSsPSbsS9kpQ4jVuVA4OX5nGLMQvkAnFMFeCSKRleEBMYdf2zogfYQP2/CkYpIfc0IGHgwyoAtNKgnACkoeWgMKu+3I62vHCqmLFcgQLyVMvL+oLfJuuvArn7TY8f1QPR7sihF7GnW1iND5aj8cPGs88WRhI=
        System.out.println("密文:" + messageEn);
        System.out.println("加密消耗时间:" + (System.currentTimeMillis() - temp) / 1000.0 + "秒");//加密消耗时间:0.392秒
        temp = System.currentTimeMillis();
        String messageDe = decrypt(messageEn, keyMap.get(1));
        System.out.println("解密:" + messageDe);
        System.out.println("解密消耗时间:" + (System.currentTimeMillis() - temp) / 1000.0 + "秒");//解密消耗时间:0.017秒
    }
}
