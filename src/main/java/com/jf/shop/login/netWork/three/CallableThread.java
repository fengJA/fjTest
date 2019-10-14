package com.jf.shop.login.netWork.three;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class CallableThread {

    public static void main(String[] args) {
        FutureTask fs = new FutureTask(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        });
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.execute(fs);
        es.submit(fs);
    }
}
