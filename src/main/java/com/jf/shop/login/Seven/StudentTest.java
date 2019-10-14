package com.jf.shop.login.Seven;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StudentTest {

    public static void main(String[] args){


        try {
            Method sins = Math.class.getDeclaredMethod("sin", double.class);
            Double invoke = (Double) sins.invoke(null, new Long(1));
            System.out.println(invoke);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //使用反射修改一个类里面的私有属性
       /* Student stu = new Student();
        Class<? extends Student> aClass = stu.getClass();
        System.out.println(aClass.getCanonicalName());

        try {
            Field id = aClass.getDeclaredField("id");
            System.out.println("id-before"+stu.getId());

            id.setAccessible(true);
            id.setInt(stu, 10);

            System.out.println("id-after"+stu.getId());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/
    }
}
