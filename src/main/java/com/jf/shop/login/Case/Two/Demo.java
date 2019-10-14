package com.jf.shop.login.Case.Two;

import java.math.BigDecimal;
import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {

       /* System.out.println("out");
        System.err.println("可以手动输出一个错误信息");*/

       //以下向文件log.txt中输出了信息
        /*try {
            PrintStream out = System.out;
            PrintStream ps = new PrintStream("D://log1.txt");
            System.setOut(ps);
            int age = 12;
            System.out.println("年龄定义成功");
            String sex = "女";
            System.out.println("性别定义成功 ");
            String info = age+","+sex;
            System.out.println("输出了info"+info);
            System.setOut(out);
            System.out.println("over");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        //简单的加密
        /*Scanner sc = new Scanner(System.in);
        System.out.println("输出一串英文字符");
        String s = sc.nextLine();
        char[] chars = s.toCharArray();
        for (int i = 0;i < chars.length;i++){
            chars[i] = (char) (chars[i] ^ 10);
        }

        //System.out.println(chars);
        System.out.println(new String(chars));*/

        //杨辉三角形
        int[][] trangle = new int[8][];
        for(int i = 0; i < trangle.length;i++){
            //初始化第二层数组大小
            trangle[i] = new int[i+1];
            for (int j = 0;j < trangle[i].length;j++){
                if(i == 0 || j ==0 || j == trangle[i].length-1){
                    trangle[i][j] = 1;
                }else {
                    trangle[i][j] = trangle[i-1][j] + trangle[i-1][j-1];
                }

                System.out.print(trangle[i][j]);
            }
            System.out.println();
        }

        String s = "张三";
        int i3 = s.hashCode();
        System.out.println(i3);

        //switch的用法
        Scanner sc4 = new Scanner(System.in);
        System.out.println("请输入员工姓名");
        String name = sc4.nextLine();
        switch (name.hashCode()){
            //这是String中对应的hashCode值，比如张三就是774889
            case 3254818:
            case 3271652:
                System.out.println("员工是："+name);
                break;
            case 2345:
            case 774889:
                System.out.println("又是一个员工："+name);
                break;
            default:
                System.out.println("没有他");
        }

        Scanner sc1 = new Scanner(System.in);
        System.out.println("输入一个整数");
        int i = sc1.nextInt();
        String s1 = i / 2 == 0 ? "o" : "j";
        System.out.println(s1);


        BigDecimal b = new BigDecimal(new String("11.1"));
        BigDecimal c = new BigDecimal(new String("1.2"));
        BigDecimal add = b.add(c);
        System.out.println(add);


        //实现两个数互换
        Scanner sc2 = new Scanner(System.in);
        System.out.println("输入一个整数");
        int i1 = sc2.nextInt();
        System.out.println("输入一个整数");
        int i2 = sc2.nextInt();
        System.out.println("执行变量互换");
        i1 = i1^i2;
        i2 = i2^i1;
        i1 = i1^i2;
        System.out.println("i1="+i1+"\ti2="+i2);


        Scanner sc3 = new Scanner(System.in);
        System.out.println("输入年份");
        int year = sc3.nextInt();
        if(year % 4 == 0 && year % 100 != 0|| year % 400 ==0){
            System.out.println("闰年"+year);
        }else {
            System.out.println("不是闰年"+year);
        }



    }
}
