package com.jf.shop.datastructure;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author fengj
 * @date 2019/10/13 -22:57
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {
        //创建广播电台,放入到Map
        HashMap<String,HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        //将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");


        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<String>();

        for (String key:broadcasts.keySet()) {
            HashSet<String> ares = broadcasts.get(key);
            allAreas.addAll(ares);
        }
        for (String allArea : allAreas) {
            System.out.print(allArea + "   ");
        }

        /*Set<String> strings = broadcasts.keySet();
        if (strings != null){

            Iterator<String> iterator = strings.iterator();
            boolean b = iterator.hasNext();
            while (b){
                String next = iterator.next();
                HashSet<String> strings1 = broadcasts.get(next);
                for (String s : strings1) {
                    allAreas.add(s);
                }
                iterator.remove();
            }

            System.out.println(allAreas);
            for (String allArea : allAreas) {
                System.out.print(allArea + "   ");
            }
        }else {
            System.out.println("========");
        }*/
    }
}
