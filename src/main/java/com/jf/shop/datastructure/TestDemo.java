package com.jf.shop.datastructure;

/**
 * @author fengj
 * @date 2019/10/11 -22:23
 */
public class TestDemo {
    public static void main(String[] args) {
        int[][] array = new int[5][5];
        int[] arra = new int[5];
        System.out.println("====================");


        for (int[] roe : array){
            for (int a : roe){
                System.out.print(a);
            }
            System.out.println();
        }
        System.out.println("==============");

        for (int i = 0; i < 5; i++) {
            array[i][arra[i]] = 8;
            arra[i]++;
        }

        for (int i = 0; i < 5; i++) {

            if (arra[i] != 0){
                for (int[] a:array){

                    for (int s : a){

                        System.out.print(s);
                    }
                    System.out.println();
                }
            }
        }


        System.out.println("==============");

                for (int[] a:array) {
                    for (int s : a){

                        System.out.print(s);
                    }
                    System.out.println();
                }

    }
}
