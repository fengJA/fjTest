package com.jf.shop.datastructure;

/**
 * @author fengj
 * @date 2019/10/13 -21:09
 * 暴力匹配匹配字符串，并返回第一个匹配上的最开始的下标
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //测试暴力匹配算法
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你~";
        int index = violenceMatch(str1, str2);
        System.out.println("index=" + index);

    }

    public static int violenceMatch(String str1,String str2){ // str1是要匹配的字符串，str2是配匹配的字符串
        char[] st1Array = str1.toCharArray();
        char[] st2Array = str2.toCharArray();
        int st1Len = st1Array.length;
        int st2Len = st2Array.length;

        int i = 0;
        int j = 0;

        while (j <= st2Len){
            if (st1Array[i] == st2Array[j]){
                i++;
                j++;
            }else {
                i = i - (j -1);
                j = 0;
            }
        }
        if (j == st1Len){
            return i - j;
        }else {
            return -1;
        }
    }

}
