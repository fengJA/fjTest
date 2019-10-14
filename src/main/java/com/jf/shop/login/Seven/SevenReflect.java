package com.jf.shop.login.Seven;

public class SevenReflect {

    public static void main(String[] args) {

        try {
            Class<?> c = Class.forName("java.util.List");
            System.out.println(c.getCanonicalName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
