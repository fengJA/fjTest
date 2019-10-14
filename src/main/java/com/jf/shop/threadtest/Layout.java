package com.jf.shop.threadtest;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author fengj
 * @date 2019/10/7 -16:32
 */
public class Layout {
    static L l = new L();


    public static void main(String[] args) {

        // 打印l对象的hashCode值，16进制方式
        System.out.println("l对象的16进制hashCode值：" + Integer.toHexString(l.hashCode()));

        //打印对象的结构：对象头、实例数据、对其填充字节
        System.out.println(ClassLayout.parseInstance(l).toPrintable());

        synchronized (l) { // 加锁，会修改l对象的对象头
            System.out.println("lock1...");
            System.out.println(ClassLayout.parseInstance(l).toPrintable());
        }

        /*Thread thread = new Thread(){
            @Override
            public void run() {
                test();
            }
        };

        thread.start();*/

    }

    public static void test(){
        synchronized (l) {
            System.out.println("test .....");
            System.out.println(ClassLayout.parseInstance(l).toPrintable());
        }
    }
}
