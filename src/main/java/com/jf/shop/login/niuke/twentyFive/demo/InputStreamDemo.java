package com.jf.shop.login.niuke.twentyFive.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InputStreamDemo {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("b.txt");

            int a = 0;
            int b = 1024;
            int read = 0;
            byte[] bytes = new byte[b];
            /*for (int i = 0; i < bytes.length; i++) {

                try {
                    read = fis.read(bytes, 0, bytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (read == -1){
                    break;
                }
                bytes[i] = (byte) read;
                //打印ellohellothishellothis
//                System.out.print(new String(bytes,0,read));
            }*/

            while (a < b){
                try {
                     read = fis.read(bytes, a, b - a);
                    if (read == -1) break;
                    a += read;
                    System.out.println(a);//23
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //打印hellohellothishellothis
            System.out.println(new String(bytes,0,a));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
