package com.jf.shop.login.Case.Two;

public class Demo2 {

    public static void main(String[] args) {
        printCart(10);
    }

    public static void printCart(int size){
        if(size % 2 ==0){
            //计算菱形长度
            size++;
        }
        for (int i = 0;i < size/2 + 1;i++){
            for (int j = size/2 + 1;j > i + 1;j--){
                System.out.print(" ");
            }
            for (int j = 0;j < 2 * i + 1;j++){
                if(j == 0||j == 2*i){
                    System.out.print("*");
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.println(" ");
        }

        for (int i = size/2 + 1;i < size;i++){
            //输出菱形左下角空白
            for (int j = 0;j < i - size/2;j++){
                System.out.print(" ");
            }
            for (int j = 0;j < 2 * size - 1 - 1 * i;j++){
                //输出菱形下半部分边缘
                if(j == 0||j == 2 * (size - i - 1)){
                    System.out.print("*");
                }
                //输出中间空白部分
                else {
                    System.out.print(" ");
                }
            }
            System.out.println(" ");
        }

    }


}
