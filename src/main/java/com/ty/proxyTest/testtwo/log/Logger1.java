package com.ty.proxyTest.testtwo.log;

public class Logger1 {
    private  String  name;
    public void hello(String name) {
        System.out.println("hello: " + name);
    }

    public void hello() {
        System.out.println("hello world");
    }
}

