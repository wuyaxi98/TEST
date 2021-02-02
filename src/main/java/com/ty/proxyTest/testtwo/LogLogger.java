package com.ty.proxyTest.testtwo;

public class LogLogger extends LogTest.Logger1 {
    @Override
    public void hello(String name) {
        System.out.println("开始执行hello方法");
        super.hello(name);
        System.out.println("结束执行hello方法");
    }

    @Override
    public void hello() {
        super.hello();
    }
}
