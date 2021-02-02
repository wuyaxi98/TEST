package com.ty.proxyTest.testtwo;

public class Father {
    private String name;
    private  int age;

    public Father(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Father() {
    }

    public void  eat(String food, String drink){
        System.out.println("有吃有喝");

    }


}
