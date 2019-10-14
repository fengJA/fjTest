package com.jf.shop.login.MapTest;


public class Student implements Comparable <Student>{
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public int compareTo(Student s) {

        int num = new Integer(this.age).compareTo(new Integer(s.age));
        if(num == 0) {
            return this.name.compareTo(s.name);
        }
        return num;
    }

    @Override
    public int hashCode() {
        return age;
    }

    /*public int hashCode(Object obj) {
        return age*32;
    }*/

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Student)){
            throw new ClassCastException("传入的类类型异常");
        }
        Student s = (Student)obj;
        if(this.age == s.age && this.name.equals(s.name)){
            return true;
        }
        return false;
    }


}
