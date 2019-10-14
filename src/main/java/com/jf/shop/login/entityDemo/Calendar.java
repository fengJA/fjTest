package com.jf.shop.login.entityDemo;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

public class Calendar {

    public static void main(String[] args) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        //c.getTime();
        //c.getTimeInMillis();
        Date data = c.getTime();
        System.out.println(data);
        Date d = new Date();
        System.out.println(d);

        java.util.Calendar c1 = java.util.Calendar.getInstance();
        c1.set(2001,1,12,8,22,13);
        c1.setLenient(true);
        c1.set(java.util.Calendar.MONTH,14);
        System.out.println(c1);
        c1.add(java.util.Calendar.YEAR,2);
        System.out.println(c1);

        //获取指定时区的时间
        Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());

        //一段连续的时间
        Duration duration = Duration.ofMillis(120000);
        long toMillis = duration.toHours();
        System.out.println(toMillis);

        Instant instant = Instant.now();
        System.out.println(instant);
       /* Instant instant1 = Instant.parse("2019-05-30T14:48:05.090");
        System.out.println(instant1);
        instant1.plus(Duration.ofDays(12).plusDays(1).plusHours(1));
        System.out.println(instant1);*/

        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        //日期格式化
        Date dt = new Date();
        //将其设置为中国地区的时间表示形式
        Locale locale = Locale.CHINA;
        DateFormat df = DateFormat.getDateInstance(1,locale);
        System.out.println(df.format(dt));

        //
        Date da = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("Gyyyy年中的第D天");
        String format = sd.format(da);
        System.out.println(format);

        try {
            String str = "13##3##2";
            SimpleDateFormat sd1 = new SimpleDateFormat("y##MM##d");

            System.out.println(sd1.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println(111);
            return ;
        }finally {
            System.out.println(123);
        }
        //System.out.println(123);

       /* List<Integer>[] l =new List[6];
        List<String>[] s =new ArrayList[7];
       // List<Object>[] l3 = new ArrayList<Integer>[3]; //报错
        List<?>[] l1 = new ArrayList<?>[12];
        List<Integer> l2 = new ArrayList<Integer>();
        l2.add(new Integer("aa"));*/

        //annotationTest("aa");
        Calendar.annotationTest(Arrays.asList("Today is a nice day"),Arrays.asList("Today is a nice day!!"));




    }
    @SuppressWarnings(value = "unchecked")
    public static <T> void test(T[] t, Collection<T> c){
        //List<String> l3 = new ArrayList<>();
        List<String> l3 = new ArrayList();
    }

    @SafeVarargs
    @SuppressWarnings(value = "unchecked")
    public static void annotationTest(List<String> ...lists){
        /*List<Integer> li = new ArrayList<>();
        li.add(3);*/

       /* List li1 = new ArrayList<Integer>();
        li1.add(3);*/
        List[] li2 = lists;
        List<Integer> li1 = new ArrayList<Integer>();
        li1.add(3);
        li2[0] = li1;
        //运行时会报错,不加@SafeVarargs的话
        String s = lists[0].get(0);
        System.out.println(s);
    }
    @SafeVarargs
    public static <T> void test1(T ... infos){
        for(T info : infos){
            System.out.println(info);
        }


    }
}





















