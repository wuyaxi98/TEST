package com.ty.proxyTest.testtwo.log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TransactionalInvocationHandler implements InvocationHandler {
    private Object target;
    public TransactionalInvocationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        System.out.println("我是动态代理对象");
        System.out.println("开启事务..");
        Object result = null;
        try {
            result = method.invoke(target, args);
            System.out.println("提交事务");
        } catch(Exception e) {
            System.out.println("回滚事务");
            throw e;
        }
        System.out.println("-----------------------");
        return result;
    }
}