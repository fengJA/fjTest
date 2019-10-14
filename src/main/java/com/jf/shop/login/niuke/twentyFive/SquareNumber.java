package com.jf.shop.login.niuke.twentyFive;

public class SquareNumber {
    public static void main(String[] args) {


        //对称平方数
        int result = 0;
        flag: for (int n = 1; n < 256 ; n++){
            result = n * n;
            String s = String.valueOf(result);
//            char[] array = new int[s.length()];
            char[] array=s.toCharArray();
//            for (int i = 0;i < s.length();i ++){
//                array[i] = Integer.parseInt(String.valueOf(s.charAt(i)));
//            }
            for (int l = 0,h = s.length() - 1; l < s.length()/2; l++, h--) {
                if (array[l] != array[h]){
                    continue flag;
                }
            }
            System.out.println(n);
        }
        //方法二:m是将原num反转：8*8=64,m=46,则不输出，11*11=121，m=121，相同
        int num,temp,m;
        for (int i = 1; i < 256; i++) {
            num = i * i;
            m = 0;
            while (num > 0) {
                temp = num % 10;
                m = temp + m * 10;
                num = num / 10;
            }
            System.out.println(m);
            if (m == i * i)
                System.out.println(i);
        }
    }
}
