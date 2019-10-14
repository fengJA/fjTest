package com.jf.shop.datastructure;

import java.io.*;

/**
 * @author fengj
 * @date 2019/10/8 -21:05
 * 稀疏数组
 */
public class SparseArray {
    public static void main(String[] args) {
        int[][] array = new int[11][11];

        array[1][2] = 2;
        array[2][3] = 2;

        for (int[] row: array) {
            for (int data : row){
                System.out.print(data);
            }
            System.out.println();
        }

        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (array[i][j] != 0){
                    sum++;
                }
            }
        }

        int count = 0;
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;


        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] != 0){
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = array[i][j];
                }
            }
        }

        System.out.println("=======稀疏数组=====");

        // 输出稀疏数组
        for (int[] row : sparseArray){
            for (int data : row){
                System.out.print(data + " ");
            }
            System.out.println();
        }

        //将数组写入文件
        Writer out = null;
        try {
            out = new BufferedWriter(new FileWriter(new File("d:\\data.txt")));
            for (int[] row : sparseArray){
                for (int data : row){
                    out.write(String.valueOf(data));
                }
                out.write("\r\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 从问价中读取二维数组
        BufferedReader read = null;
        int[][] parseRead = new int[3][3];

        try {
            read = new BufferedReader(new FileReader("d:\\data.txt"));
            String line;
            int row = 0;
            while ((line = read.readLine()) != null){
                String[] temp = line.split("\t");


                for (int i = 0; i < temp.length; i++) {
                    parseRead[row][i] = Integer.parseInt(temp[i]);
                }
                row ++;
            }

            for (int[] a1 : sparseArray){
                for (int data : a1){
                    System.out.print(data + " ");
                }
                System.out.println();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (read != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
