package com.jf.shop.login.entityDemo;

import java.io.IOException;
import java.math.BigDecimal;

public class Demon {

    private static int BORDER_SIZE = 15;
    private String [][] border;

    public void initBorder(){
        border = new String[BORDER_SIZE][BORDER_SIZE];
        for (int i = 0;i<BORDER_SIZE;i++){
            for (int j = 0;i<BORDER_SIZE;i++){
               border[i][j] = "+";
            }
        }
    }

    public void printBorder(){
        for (int i = 0;i<BORDER_SIZE;i++){
            for (int j = 0;i<BORDER_SIZE;i++){
                System.out.print(border[i][j]);
            }
            System.out.println("\n");
        }
    }

    public static void main(String[] args)throws IOException {

        /*Demon gb = new Demon();
        Demon de = new Demon();
        gb.initBorder();
        gb.printBorder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
        String inputStr = null;
        while ((inputStr = br.readLine())!=null){
            String[] postStrArr = inputStr.split(",");
            int xPos = Integer.parseInt(postStrArr[0]);
            int yPos = Integer.parseInt(postStrArr[1]);
            gb.border[yPos-1][xPos-1] = "。";
            gb.printBorder();

            Random rand = new Random();
            rand.nextInt(15);//取值0到15
            *//*int v = (int)Math.random() * 15;
            int v1 = (int)Math.random() * 15;
            de.border[v-1][v1-1]="*";
            de.printBorder();*//*
        }*/

        /*int [][] a = new int[4][];
        for (int i = 0,len = a.length;i < len ;i++){
            System.out.println(a[i]);
        }
        a[0] = new int[2];
        a[0][1] = 6;
        for (int i= 0,len = a[0].length;i<len;i++){
            System.out.println(a[0][i]);
        }

        String[][] st = new String[][]{new String[3],new String[]{"aa"}};
        int[] array = new int[]{0,1,2,3};
        int [] b = new int[5];

        Arrays.binarySearch(array,1);//二分查找
        Arrays.sort(a);
        System.arraycopy(array, 1,b,2,2 );//复制
        Arrays.parallelSort(array);*/

        Demon s = new Demon();
        bigDecimal();

    }

    public static void bigDecimal(){
        BigDecimal bg = new BigDecimal("0.01");//以String的方式为构造器的参数，计算时比较精确
        BigDecimal f1 = BigDecimal.valueOf(0.01);//以double为参数时，尽量用valueof，计算时较精确
        BigDecimal f2 = new BigDecimal(0.01);//直接将double参数传进去，进行计算时输出时会导致很多位
        System.out.println(bg.add(f1));//精确输出
        System.out.println(f2.divide(f1));//输出不精确

        char a = '哈';
        System.out.println(a);
        short s = (short) a;
        System.out.println(s);
        short sh= 5;



    }
}
