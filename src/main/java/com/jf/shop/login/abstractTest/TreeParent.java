package com.jf.shop.login.abstractTest;

public abstract class TreeParent {

    public TreeParent(){}

    public abstract double higher();
    public abstract double longMeter();

    //线程安全部的单例模式
    static class Singleton{

        private volatile static Singleton instance;

        public static Singleton getInstance(){
            if (instance == null){
                synchronized (Singleton.class){
                    if (instance == null){
                        return new Singleton();
                    }
                }
            }
            return instance;
        }
    }
}
