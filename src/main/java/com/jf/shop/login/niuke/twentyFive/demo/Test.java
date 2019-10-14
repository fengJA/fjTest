package com.jf.shop.login.niuke.twentyFive.demo;

import java.io.File;
import java.io.IOException;

public class Test {

    private static File file = null;
    public static void main(String[] args) throws IOException{
        String str = "D:\\dd\\..\\dd\\";
        System.out.println(str);

//        System.out.println(str.substring(0, str.length()-1));
        File file = new File(str + File.separator);
        System.out.println(file);
        if (!file.exists()){
            file.mkdir();
        }
        /*if (str.endsWith("\\")){
            int i = str.lastIndexOf("\\");
            System.out.println(i);
            str.substring(i-2,str.length()-1);
            System.out.println(str);
        }*/

        /*file = new File(str) ;
        System.out.println(file.getAbsolutePath());
        String s = file.toString();
        System.out.println(file.getCanonicalPath());
        String URL = null;
//        f.getCanonicalPath();
        *//*System.out.println("as:"+f.getCanonicalPath());
        System.out.println("ac:"+f.getAbsolutePath());*//*
        if (s.contains("..")){

            file = new File(file.getCanonicalPath());
            System.out.println("as:"+file.getCanonicalPath());
            System.out.println("ac:"+file);
        }*/





       /* dis = new DataInputStream(new FileInputStream(f)) ;
        String name = null ;

        char temp[] = null ;
        int len = 0 ;
        char c = 0 ;    // '\u0000'
        try{
            while(true){
                temp = new char[200] ;
                len = 0 ;
                *//*while((c=dis.readChar())!=0){
                    temp[len] = c ;
                    len ++ ;
                }*//*
                while (dis.available()>0){
                    c = dis.readChar();
                    temp[len] = c;
                    len ++;
                }
                name = new String(temp,0,len) ;

                System.out.println(name);
                dis.close() ;
            }
        }catch(Exception e){}*/

        /*InputStreamReader fileReader = new InputStreamReader(new FileInputStream(f),"GB2312");
        BufferedReader fp = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();

        FileOutputStream fos = new FileOutputStream("D:\\b.txt");
        OutputStreamWriter out = new OutputStreamWriter(fos);

        int c;
        while ((c = fp.read()) != -1){
            sb.append((char)c);
        }
        String s1 = sb.toString();
        if (s1.contains("爸爸")){
            s1 = s1.replace("爸爸", "爷爷");
        }
        System.out.println(s1);
        out.write(s1,0,s1.length());
        out.flush();*/


        /*File file = new File("D:\\aa\\bb\\cc\\a.txt");
        File file1 = new File("D:\\ff\\f.txt" );
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(file);
            if (file.isFile()){
                String fileOut = file1 + file.getName();
                fos =new FileOutputStream(fileOut);
                byte[] bytes = new byte[1024];
                int len;
                while ((len = fis.read(bytes)) != -1){
                    fos.write(bytes, 0, len);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }
}
