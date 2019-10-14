package com.jf.shop.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author fengj
 * @date 2019/9/26 -20:35
 */
@RestController
public class Test {

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("test1")
    public void test1(@PathVariable("id") int id){

        restTemplate.getForEntity("http://localhost:7788/"+id,StringTest.class);
    }

    public static void main(String[] args) {
//        testClass();
        testClass2();
    }

    public void testInv(){
        Class<?> c1 = null;
        Class<?> c2 = null;
        Class<?> c3 = null;

        try {
            // 获取StringTest类的三种方法
            c1 = Class.forName("com.jf.shop.base.StringTest");

//            c2 = new StringTest().getClass();

            c3 = StringTest.class;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void testClass(){
        Class<?> c1 = null;

        try {
            c1 = Class.forName("com.jf.shop.base.StringTest");
            // 使用空参构造方法
            StringTest st = (StringTest) c1.newInstance();

            st.setName("zs");
            st.setAge(11);

            System.out.println(st); // StringTest(name=zs, age=11)
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void testClass2(){
        Class<?> c1 = null;

        try {
            c1 = Class.forName("com.jf.shop.base.StringTest");
            // 使用带参构造方法
            Constructor<?>[] con = c1.getConstructors();
            System.out.println(con);
            StringTest st = (StringTest) con[0].newInstance("ls",11);

            System.out.println(st); // StringTest(name=ls, age=11)
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    // 利用反射调用类里面的方法
    public void testMethod(){
        Class<?> c1 = null;

        try {
            c1 = Class.forName("com.jf.shop.base.StringTest");

            // 调用有参方法
            Method reviseStr = c1.getMethod("reviseStr", String.class);
            reviseStr.invoke(c1.newInstance(),"hello");

            // 调用无参方法
            Method add = c1.getMethod("add");
            add.invoke(c1.newInstance());


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void testGet(){
        Class<?> c1 = null;
        Object obj = null;
        try
        {
            c1 = Class.forName("com.jf.shop.base.StringTest");
            obj = c1.newInstance();
            /*setter(obj, "name", "xy", String.class);
            setter(obj, "age", 20, int.class);
            getter(obj, "name");
            getter(obj, "age");*/
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
