package com.jf.shop.login.niuke.twentyFive;

import java.util.HashSet;
import java.util.Set;

public class stamp {

    public static void main(String[] args) {
        //打印邮票
        int result = 0;
        int a = 8,b = 10 , c = 18;
        Set<Integer> set = new HashSet<>();
        set.add(a);
        set.add(b);
        set.add(c);
        System.out.println(set.size());
        for (int d = 0 ; d < 6; d++){
            for (int e = 0 ; e < 5; e++){
                flag:for (int f = 0 ; f < 7; f++){
                    result = a * d + b * e + c * f;
                    set.add(result);
                }
            }
        }
        System.out.println(result);
        System.out.println(set.size()-1);
    }



}
