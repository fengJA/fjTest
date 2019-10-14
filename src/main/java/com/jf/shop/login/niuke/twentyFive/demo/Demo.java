package com.jf.shop.login.niuke.twentyFive.demo;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Demo {
    //字节流转字符流，并且设置编码格式
    private static String getOuterIp() throws IOException {
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL("http://1212.ip138.com/ic.asp");
            URLConnection urlconnnection = url.openConnection();
            inputStream = urlconnnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "GB2312");  //字节流转字符流，并且设置编码格式
            bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer webContent = new StringBuffer();
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                webContent.append(str);
            }
            int ipStart = webContent.indexOf("[") + 1;
            int ipEnd = webContent.indexOf("]");
            return webContent.substring(ipStart, ipEnd);
        } finally {
            if (inputStream != null && bufferedReader != null) {
                inputStream.close();
                bufferedReader.close();
            }
        }
    }

    FileInputStream f;
    {
        try {
            f = new FileInputStream("texy.text");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(23);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{

        /*try(BufferedOutputStream bf = new BufferedOutputStream(new FileOutputStream("b.txt",true))) {
            bf.write("aa".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /*BufferedOutputStream bf = new BufferedOutputStream(new FileOutputStream("b.txt",true));
        bf.write("hello".getBytes());
        bf.write("this".getBytes());
        //这里如果不写flush()方法，就不能write（）写进去，当然，close()方法也有刷新的效果
        bf.flush();
        bf.close();*/

        // FileInputStream
        FileInputStream fis;
        try {
             fis = new FileInputStream("a.txt");
             int len =0;
            byte[] b = new byte[1024];
            /*while ((len = fis.read(b))!= -1){
                //打印输出a.txt文件的内容hellohellobcd，如果new byte[1024]大小大于文件的大小，则一次性输出，如果小于，则每次new byte[3]取3个
                System.out.println(new String(b, 0, len));
//                System.out.println(b);
            }
            //打印输出  b:[B@776ec8df
            System.out.println("b:"+b.toString());*/
            for (int i = 0; i < b.length; i++) {
                int read = fis.read(b, 0, b.length);
                if (read == -1){
                    break;
                }
                b[i] = (byte) len;
                System.out.print(new String(b,0,read));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        /*FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("a.txt",true);
            *//*BufferedOutputStream buffer = new BufferedOutputStream(outputStream);
            buffer.write("hello".getBytes());
            byte[] bys={97,98,99,100,101};
            buffer.write(bys,1,3);
            outputStream.write("hello".getBytes());*//*
            *//*for (int i = 0; i <5 ; i++) {
                outputStream.write("hello".getBytes());
//                outputStream.write("\n\r".getBytes());
                outputStream.write(12);
//                outputStream.write("\n\r".getBytes());
                outputStream.write("girl".getBytes());
            }*//*
            outputStream.write("hello".getBytes());
            byte[] bys1={97,98,99,100,101};
            //会在a.txt文件中输出字符abc
            outputStream.write(bys1,1,3);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/


        //ByteArrayOutputStream的用法
        /*ByteArrayOutputStream out = new ByteArrayOutputStream(5);
        while (out.size() < 12){
            try {
                out.write(System.in.read());
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("out:"+out);

        //创建一个b数组，其大小和里面的内容与out一样
        byte[] b = out.toByteArray();
        for (int i = 0; i < b.length; i++) {
            System.out.println("b:"+(char)b[i]);
        }

        ByteArrayInputStream in = new ByteArrayInputStream(b);
        int s;
        for (int x = 0; x < b.length; x++) {
            if ((s = in.read())!=-1){
                System.out.println(Character.toUpperCase((char) s));
            }
        }*/

    }


}
