package com.jf.shop.login.netWork.three;

import javax.xml.bind.DatatypeConverter;

public class ThreadTest {
    public static void main(String[] args) {
        //方法一：
        //有可能不能打印出结果，因为调用getDigist()方法时，与可能线程还没有结束，digest中没有东西，为空,
        // 可能结果：抛出NullPointException异常，线程挂起（没有任何输出），得到正确的输出
        for (String fileName : args){
            ReturnThread rt = new ReturnThread(fileName);
            Thread t = new Thread(rt);
            t.start();

            StringBuilder sb = new StringBuilder(fileName);
            sb.append(": ");
            byte[] digest = rt.getDigist();
            sb.append(DatatypeConverter.printHexBinary(digest));
            System.out.println(sb);
        }

        //方法二：
        //该方法也有风险，有可能第一个for循环执行较快，完了后，线程还没有执行完，则digst中也没有数据，
        // 可能结果：抛出NullPointException异常，线程挂起（没有任何输出），得到正确的输出
        ReturnThread[] re = new ReturnThread[args.length];
        for (int i = 0; i < args.length; i++) {
            re[i] = new ReturnThread(args[i]);
            Thread t = new Thread(re[i]);
            t.start();
        }

        for (int i = 0; i < args.length; i++) {
            StringBuilder sb = new StringBuilder(args[i]);
            sb.append(": ");
            byte[] digest = re[i].getDigist();
            sb.append(DatatypeConverter.printHexBinary(digest));
            System.out.println(sb);
        }

        //解决方法一：轮询
        //该方法不能保证一定可用，有些虚拟机的主线程会占据所有可用的时间，而没有给具体的工作留出时间
        ReturnThread[] re1 = new ReturnThread[args.length];
        for (int i = 0; i < args.length; i++) {
            re1[i] = new ReturnThread(args[i]);
            Thread t = new Thread(re1[i]);
            t.start();
        }

        for (int i = 0; i < args.length; i++) {
            while (true){
                byte[] digest = re1[i].getDigist();
                //给出一个状态标识位，保证里面有数据
                if (digest != null){
                    StringBuilder sb = new StringBuilder(args[i]);
                    sb.append(": ");
                    sb.append(DatatypeConverter.printHexBinary(digest));
                    System.out.println(sb);
                    break;
                }
            }
        }
    }
}
