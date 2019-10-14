package com.jf.shop.login.MapTest;

import java.util.*;

public class EnumMapTest {

    public static void main(String[] args) {
        EnumMap enumMap = new EnumMap(Season.class);

        enumMap.put(Season.SUMMER, "hot");
        enumMap.put(Season.SPRING, "cool");

        Set set = enumMap.keySet();
        Collection values = enumMap.values();
        Set set1 = enumMap.entrySet();

        System.out.println(set);
        System.out.println(values);
        System.out.println(set1);

        Map map = new HashMap();
        map.put("aa", 1);


        map.merge("aa",10,(oldVal,param) -> (Integer)oldVal + (Integer)param);
       // System.out.println(map);

        map.computeIfAbsent("bb",key -> ((String)key).length());
        //System.out.println(map);
        map.forEach((key,value)-> System.out.println(key+","+value));

        map.compute("cc",(k,v) -> ((String)k).length());
        System.out.println(map);
        Collection c = new HashSet();
        c.add(1);
        c.add(2);
        Iterator it = c.iterator();
        while (it.hasNext()){
            Integer next = (Integer) it.next();
            //System.out.println(next);
            if(next.equals(1)){
                it.remove();
            }
            it.forEachRemaining(obj -> System.out.println(obj));
        }

    }
}
