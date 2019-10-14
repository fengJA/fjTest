package com.jf.shop.login.algorithm;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.utils.DateUtils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class MD5Test {
    private static final String DATE_PARTTERN= "yyyy-MM-dd";
    private static final String key = "Iamkey";
    public static void main(String[] args) {

        String content = "you are my son"; // 原文
        try {
            byte[] a;
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            a = messageDigest.digest(content.getBytes());
            System.out.println(byte2hex(a)); // 333a9634d8809b5a9e8d280d82553b8fd8d4a911

            messageDigest = MessageDigest.getInstance("SHA-256");
            a = messageDigest.digest(content.getBytes());

            messageDigest = MessageDigest.getInstance("MD5");
            a = messageDigest.digest(content.getBytes());
            System.out.println(byte2hex(a)); // 6fe6b9a8f8bd29f4f4f1368a0619a7ae

            //将byte[]数组转换为十六进制数据,java自定义类
            String s = DatatypeConverter.printHexBinary(a);//a输出是大写的十六进制
            System.out.println("s:"+s.toLowerCase());//s:6fe6b9a8f8bd29f4f4f1368a0619a7ae
            //将byte[]数组转换为十六进制数据,自己实现方法
            String s1 = MD5Test.bytesToHex(a);
            System.out.println("s1:"+s1.toLowerCase());

            // 第三方 MD5 算法。需要添加 jar 包 org.apache.commons.codec.digest.DigestUtils，或者导入依赖
            String encodeStr=DigestUtils.md5Hex(content);
            System.out.println(encodeStr); // 6fe6b9a8f8bd29f4f4f1368a0619a7ae

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String byte2hex(byte[] b) //二进制转字符串
    {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs;
    }

    //将byte[]数组转换为十六进制数据
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }


    public static String md5(String text,String key){
        String md5 = DigestUtils.md5Hex(text + key);
        return md5;
    }

    public static String md5Date(){
        String md5 = DigestUtils.md5Hex(key + DateUtils.formatDate(new Date(), DATE_PARTTERN));
        return md5;
    }


}
