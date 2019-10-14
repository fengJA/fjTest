package com.jf.shop.login.shopTeminal.responseEntity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jf.shop.login.shopTeminal.utils.HttpRequestUtil;
import com.jf.shop.login.shopTeminal.utils.ResultModel;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterThread{

    public static void main(String[] args) throws Exception{
        // 开始的倒数锁
        final CountDownLatch begin = new CountDownLatch(1);

        // 结束的倒数锁
        final CountDownLatch end = new CountDownLatch(10);

        // 十名选手
        final ExecutorService exec = Executors.newFixedThreadPool(10);

        for (int index = 0; index < 10; index++) {
            final int NO = index + 1;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // 如果当前计数为零，则此方法立即返回。
                        // 等待
                        begin.await();
                        doRegister();
                    } catch (InterruptedException e) {
                    } finally {
                        // 每个选手到达终点时，end就减一
                        end.countDown();
                    }
                }
            };
            exec.submit(run);
        }
        System.out.println("Game Start");
        // begin减一，开始游戏
        begin.countDown();
        // 等待end变为0，即所有选手到达终点
        end.await();
        System.out.println("Game Over");
        exec.shutdown();
    }


    public static void doRegister() {
        GotoRegisterEntity gotoRegisterEntity = new GotoRegisterEntity();
        gotoRegisterEntity.setUsername("zs");
        gotoRegisterEntity.setPassword("123");
//        gotoRegisterEntity.setPasswordAgain("111");

//        String url = CommonApi.BASE_URL + CommonApi.GOTO_REGISTER;
        String url1 = "http://10.9.5.24:9000/ff/login/goto_login";
        HashMap<String,String> header = new HashMap<>();
        header.put("AccessToken","fdfef286-849b-40ba-886e-769b4f802edb");
        String sr= HttpRequestUtil.sendPost(url1, JSONObject.toJSONString(gotoRegisterEntity),header,false);

        ResultModel goToLoginResponseEntityResultModel = JSON.parseObject(sr, new TypeReference<ResultModel>() {
        });
        System.out.println(JSONObject.toJSONString(goToLoginResponseEntityResultModel));
    }
}
