package com.jf.shop.login.entityDemo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Test {

    public static void main(String[] args) {
        String st1 = "啊啊啊";
        System.out.println(st1.length());
        byte[] bytes = st1.getBytes();
        System.out.println(bytes.length);

        try {
            InputStreamReader s1 = new FileReader("d:");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

       //File f = File.createTempFile("aa", null);

    }
}
