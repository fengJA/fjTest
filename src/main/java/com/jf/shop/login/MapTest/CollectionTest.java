package com.jf.shop.login.MapTest;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class CollectionTest {

    public static void main(String[] args) {

        Collection books = new HashSet();
        books.add(new String("两点二十了"));
        books.add(new String("疯狂的人生是啥"));
        books.add(new String("世界如此美好"));
        books.add(new String("夏天很热啊"));
        books.removeIf(ele ->((String)ele).contains("两"));
//        System.out.println(books);
//        System.out.println(calAll(books, ele -> ((String)ele).length()>5));
//        System.out.println(calAll(books, ele -> ((String)ele).contains("好")));

        IntStream is = IntStream.builder().add(11).add(1).add(80).build();
        //System.out.println(is.max());
        //System.out.println(is.min().getAsInt());

        IntStream newIs = is.sorted();
        //newIs.forEach(obj -> System.out.println(obj));
        System.out.println(newIs.min());

    }

    public static int calAll(Collection books, Predicate p){
        int total = 0;
        for (Object book : books) {
            if(p.test(book)){
                total++;
            }
        }
        return total;
    }


}
