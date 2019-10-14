package com.jf.shop.login.Case.Two.Four;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Demo1 {
    public static void main(String[] args) {

        Double v1 = 1.0;
        Double v2 = 1.0;
        NumberFormat nf = new DecimalFormat("0");
        String s1 = nf.format(v1);
        String s2 = nf.format(v1);
        System.out.println("v1和v2:"+s1+","+s2);

        BigDecimal b1 = BigDecimal.valueOf(1);
        BigDecimal b2 = BigDecimal.valueOf(2);
        System.out.println(b1.add(b2).toString());
        String s3 = "1";
        System.out.println(s3);
        Double d= 2.3;
        System.out.println(d.toString());


        /**
         * 反序输出字符窜
         */
        String s = "asdffgh";
        //利用char[]反序输出字符窜
        char[] c = s.toCharArray();
        for (int i = c.length-1;i>=0;i--){
            System.out.print(c[i]);

        }
        //利用StringBuilder反序输出字符窜
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        System.out.println(sb);

        /**
         * List集合的元素转换
         */
       /* List<String> l1 = new LinkedList<>();
        l1.add("11");
        l1.add("22");
        System.out.println(l1.get(0));
        List<Integer> l2 = new ArrayList<>();
        //输出l1中的元素
        l1.stream().forEach(l -> System.out.println(l));

        //将l1中的元素复制到l2中
        for (String l : l1) {
            l2.add(Integer.parseInt(l));
        }
        System.out.println(l2);*/
    }
}
