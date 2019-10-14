package com.jf.shop.login.MapTest;

import java.util.HashMap;

public class StudentHashMapTest {

    public static void main(String[] args) {
        HashMap<Student,String> hs = new HashMap<>();

        hs.put(new Student("zs", 11), "aa");
        hs.put(new Student("ls", 11), "aa");
        hs.put(new Student("wu ", 2), "bb");

        hs.put(new Student(null, 0), "cc");

        hs.put(null, "dd");

        System.out.println(hs);
    }
}
