package com.ty.proxyTest.testtwo;

import java.lang.reflect.Proxy;

public class LogTest {

    public static void main(String[] args) {
        Logger logger1 = new Logger1();
        TransactionalInvocationHandler  transHandler = new TransactionalInvocationHandler(logger1);
        Logger transLogger = (Logger) Proxy.newProxyInstance(LogTest.class.getClassLoader(), new Class[] { Logger.class }, transHandler);
        LogInvocationHandler logHandler = new LogInvocationHandler(transLogger);
        Logger logLogger = (Logger) Proxy.newProxyInstance(LogTest.class.getClassLoader(), new Class[] { Logger.class }, logHandler);
        logLogger.hello("world");
    }

    public static class Logger1 implements Logger {

        public void hello(String name) {
            System.out.println("hello: " + name);
        }

        @Override
        public void hello() {
            System.out.println("hello world");
        }
    }
}
