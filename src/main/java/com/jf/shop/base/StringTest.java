package com.jf.shop.base;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author fengj
 * @date 2019/9/26 -12:05
 */
@Data
@AllArgsConstructor
//@NoArgsConstructor
public class StringTest {

    public String name;
    public int age;


    public static void main(String[] args) {
        String str = "helloword";
        char[] chars = str.toCharArray();
        String reverse = "";
        for (int i = 0; i < chars.length; i++) {
            reverse += chars[i];
        }
        System.out.println(reverse);
    }

    public String reviseStr(String str){
        int length = str.length();
        String revise = "";
        for (int i = 0; i < length; i++) {
            revise = str.charAt(i) + revise;
        }

        return revise;
    }

    public void arrayRev(int[] array){

        for (int i = 0; i <array.length ; i++) {
            int temp = array[array.length - i -1];
            array[array.length - i -1] = array[i];
            array[i] = temp;
        }
    }
}
