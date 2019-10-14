package com.jf.shop.login.niuke.twentyFive.demo;

import java.io.*;

public class FileCopy {
//    private static FileInputStream fis = null;
//    private static FileOutputStream fos = null;
//    private static InputStreamReader reader = null;
//    private static OutputStreamWriter writer = null;
//    private static BufferedReader br = null;

//    private static String source = "D:\\aa\\..\\aa\\";
    public static void main(String[] args) {
        String source = "D:\\aa\\..\\aa\\";
        String target = "D:\\yy\\..\\yy";
        if (source.endsWith("\\")){
            source = source.substring(0, source.length()-1);
            System.out.println("url:"+source);
        }

        copyFile(source,source,target);
    }

    public static void copyFile(String url,String source,String target){

        FileInputStream fis = null;
        FileOutputStream fos = null;
        InputStreamReader reader = null;
        OutputStreamWriter writer = null;
        BufferedReader br = null;

        File file = new File(url);
        File[] files = file.listFiles();

//        file1 = new File(target + File.separator);
        File file1 = new File( target + File.separator);

        try {
            if (!file1.exists()){
                file1.mkdir();
            }

            System.out.println("aa"+file.getAbsolutePath());
            //对于”\..“可以直接返回上一级目录
            file = new File(file.getCanonicalPath());
//            file1 = new File(file1.getCanonicalPath());
            System.out.println("bb"+file.getAbsolutePath());
            System.out.println("cc"+file1.getAbsolutePath());


            for (File f : files) {
                String URL = file1 + f.getAbsolutePath().substring(source.length());
                File file2 = new File(URL);

                if (f.isDirectory()) {

                    file2.mkdir();
                }else {
                    fis = new FileInputStream(f.getAbsolutePath());
                    reader = new InputStreamReader(fis,"GB2312");
                    fos = new FileOutputStream(file2);
                    writer = new OutputStreamWriter(fos);
                    br = new BufferedReader(reader);

                    String str = null;
                    String s1 = null;
                    StringBuilder sb = new StringBuilder();

                    while ((str = br.readLine()) != null){
                        sb.append(str);
                        s1 = sb.toString();
                        if (s1.contains("你是猪")){
                            s1 = s1.replace("你是猪", "我是仙女");
                        }
                    }

                    writer.write(s1);
                    writer.flush();

                }
                if (f.isDirectory()){
                    copyFile(f.getAbsolutePath(),source,target);
                }

            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fis != null && fos != null){
                    fis.close();
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
