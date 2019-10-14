package com.jf.shop.login.goBang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GoBangTest {
    private String white = "白色";
    private String black = "黑色";
    private boolean color = true;

    public static void main(String[] args){
        GoBang goBang = new GoBang();
        goBang.initBorder();
        goBang.printBorder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = null;
        try {
            while ((inputStr = br.readLine()) != null) {
                Point point = goBang.splitPoint(inputStr);
                //https://blog.csdn.net/qq_20698983/article/details/80296165
                //五子棋能在网上找到例子，实际项目能在网上找到例子吗？
                //      ^(*￣(oo)￣)^
                //要耐心一些，别急
                //流于形式终究会眼高手低
                //四舍五入就等于啥都没学
//                goBang.isGoBang(point,color)
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
