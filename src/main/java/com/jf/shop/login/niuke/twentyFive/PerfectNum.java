package com.jf.shop.login.niuke.twentyFive;/*
package com.jf.shop.login.niuke.twentyFive;

import org.apache.commons.lang.StringUtils;
import java.util.*;

public class PerfectNum {
    public static void main(String[] args) {
        int a,b,m=0;
        Set<Integer> set = new HashSet<>();
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new TreeSet<>();
        set2.add(2);

        flag:for (int num = 2; num <= 60; num++) {
            for (int i = 1; i < num/2; i++) {
                a = num % i;
                b = num / i;
                if (a == 0){
                    set.add(b);
                    set.add(i);
                }
            }
            set.remove(num);
//            System.out.println("num:"+num);
            Iterator<Integer> it = set.iterator();
            List<Integer> list1 = new ArrayList<>();
            while (it.hasNext()){
                Integer next = it.next();
                list1.add(next);
            }
//            System.out.println(list1);
            for (int n = 0 ;n <set.size();n++){
                m += list1.get(n);
            }
//            System.out.println("aa:"+m);
//            System.out.println("set"+set);
            if (m == num ){
                set1.add(num);
                list1.clear();
            }else if(m > num){
                set2.add(num);
                list1.clear();
            }else {
                m = 0;
                set.clear();
                list1.clear();
//                continue flag;
            }
            m = 0;
            set.clear();
            list1.clear();
        }

        System.out.println("E:"+StringUtils.strip(set1.toString(),"[]"));
        System.out.println("G:"+StringUtils.strip(set2.toString(),"[]"));

        System.out.print("E:"+set1.toString().replace("[", "").replace("]", "").replace(",", " ")+" ");
        System.out.println("G:"+set2.toString().replace("[", "").replace("]", "").replace(",", " "));

        //方法二：
        int sum=0;
        StringBuffer wanshu=new StringBuffer("E: ");
        StringBuffer yingshu=new StringBuffer("G: 2 ");
        for(int i=2;i<=60;i++) {
            sum=0;
            for(int j=1;j<i;j++) {
                if(i % j==0) {
                    sum+=j;
                }
            }
            if(sum==i) {
                wanshu.append(i+" ");
            }else if(sum>i) {
                yingshu.append(i+" ");
            }
        }
        System.out.println(wanshu.toString()+yingshu.toString());
    }

}
*/
