package com.jf.shop.login.Seven;

import java.lang.reflect.Proxy;

public class AngencyTest {
    public static void main(String[] args) {

        ClassLoader classLoader = Sellers.class.getClassLoader();
        Sellers sellers = (Sellers) Proxy.newProxyInstance(classLoader, new Class[]{Sellers.class}, new Agency());
        sellers.sell();
        sellers.buy();
    }
}
