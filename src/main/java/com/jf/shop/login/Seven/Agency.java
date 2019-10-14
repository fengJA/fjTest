package com.jf.shop.login.Seven;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Agency implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("bbaaaaa");
        return null;
    }
}
