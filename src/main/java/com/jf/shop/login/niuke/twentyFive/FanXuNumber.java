package com.jf.shop.login.niuke.twentyFive;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FanXuNumber {
    public static void main(String[] args) {
        int result;
        int temp ,num,a;
        List<Character> list = new ArrayList();
        int b = 1000;
        for (int i = 1000; i < 9999 ;i++){
            a = i;
            result = i * 9;

            //方法一：利用Collections工具类
            String s = String.valueOf(i);
            char[] array = s.toCharArray();
//            System.out.println(array);
            for (int n = 0; n < s.length(); n++) {
                list.add(array[n]);
            }
            Collections.reverse(list);
            String str = "";
            for (char l:list){
                str +=l;
            }
            int i1 = Integer.valueOf(str).intValue();
            if (i1 == result){
                System.out.println(i);
            }
            list.clear();

            //方法二：
            /*num = 0;
            while (a > 0){
                temp = a % 10;
                num = temp + num *10;
                a = a /10;
            }
            if (num == result){
                System.out.println(i);
            }*/
        }
    }
}
