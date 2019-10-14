package com.jf.shop.login.niuke.twentyFive.demo;

import java.io.*;

public class CopyFile {
    private static String source = "D:\\aa";
    private static FileInputStream fis = null;
    private static FileOutputStream fos = null;
    private static BufferedReader br = null;

    public static void main(String[] args) {
        copyFile(source);
    }

    public static void copyFile(String url){

        File file = new File(url);
        File[] files = file.listFiles();
        File file1 = new File("D:\\yy" + File.separator);

        try {
            if (!file1.exists()){
                file1.mkdir();
            }

            for (File f : files) {
                String URL = file1 + f.getAbsolutePath().substring(source.length());
                File file2 = new File(URL);
                if (f.isDirectory()) {

                    file2.mkdir();
                }
                if (f.isDirectory()){
                    copyFile(f.getAbsolutePath());
                }

            }
        }finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
