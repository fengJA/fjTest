package com.jf.shop.login.MapTest;

import java.util.*;

public class Number {
    int count;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number = (Number) o;
        return count == number.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    public static void main(String[] args) {
        TreeSet ts = new TreeSet();
        ts.add(new String("a"));
        ts.add(new String("z"));
        ts.add(new String("bb"));
        //ts.add(new Date());//与String时不同的类型，不可以调用compareTo（）
        System.out.println(ts);

        Stack v = new Stack();
        v.push(1);
        v.peek();

        LinkedList l = new LinkedList();
        l.add(1);
        l.add(2);
        l.add(3);
        Iterator it1 = l.iterator();

        HashMap h = new HashMap();
        h.put("a", "11");
        h.put("a", "13");
        System.out.println(h);

        HashSet hs = new HashSet();
        hs.add(1);
        hs.add(1);
        System.out.println(hs);
    }
}
