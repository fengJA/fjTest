package com.jf.shop.login.netWork.three;

import java.util.concurrent.*;

public class FutureTaskThread {
    static class SumTask implements Callable<Long> {
        @Override
        public Long call() throws Exception {

            long sum = 0;
            for (int i = 0; i < 4; i++) {
                sum += i;
            }
            return sum;//6
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Start:" + System.nanoTime());
        FutureTask<Long> futureTask = new FutureTask<Long>(new SumTask());
        Executor executor= Executors.newSingleThreadExecutor();
        executor.execute(futureTask);

      /*  Long aLong = futureTask.get();//6
       System.out.println("aLong:"+aLong);//6*/
        System.out.println("center:"+ System.nanoTime());
        for(int i=0;i<5;i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    Thread thread = Thread.currentThread();
                    System.out.println( "name:"+thread.getName());
                    try {
                        System.out.println("get result "+futureTask.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    System.out.println("第:"+ System.nanoTime());
                }
            });

        }
        System.out.println(futureTask.get());
        System.out.println("End:" + System.nanoTime());
    }
}
