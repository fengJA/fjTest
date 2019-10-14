package com.jf.shop.login.algorithm;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

public class PasswordBase64 {
    public static void main(String[] args) {
        byte[] encode = Base64.getEncoder().encode("your father".getBytes());
        byte[] decode = Base64.getDecoder().decode(encode);
        System.out.println("encode:" + new String(encode));//encode:eW91ciBmYXRoZXI=
        System.out.println("decode:" + new String(decode));//decode:your father

        MD5Test();

        calendarTest();
    }

    public static String MD5Test(){
        try {
            //创建实例，可以为MD5 或者 SHA
            MessageDigest md = MessageDigest.getInstance("MD5");
            String str = "your father";
            md.update(str.getBytes());
            //signum为符号位，1--正，0--0，-1--负
            System.out.println(new BigInteger(1,md.digest()).toString(16));//ca579d7ecc639df8004c2763460b64c4
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的十六进制值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位十六进制值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1,md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void calendarTest(){
        //因为Calendar是抽象类，则用这种方式创建实例
        Calendar calendar = Calendar.getInstance();

        //将时间设置为前一天
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-1);
        Date time = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
        System.out.println(format.format(time));//2019-08-01 10:08:925
    }
}
