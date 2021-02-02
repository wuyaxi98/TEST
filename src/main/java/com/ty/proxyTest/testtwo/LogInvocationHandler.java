package com.ty.proxyTest.testtwo;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class LogInvocationHandler implements InvocationHandler {

    private static final Logger log = LoggerFactory.getLogger(LogInvocationHandler.class);

    private Object target;

    public LogInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        // 进入XXX类.XXX方法
        String targetClassName = this.target.getClass().getName();
        String targetMethodName = method.getName();
        log.info("------>进入" + targetClassName + "." + targetMethodName);
        // 传入参数：
        // xxx = xxx
        // yyy = yyy
        Parameter[] params = method.getParameters();
        if (ArrayUtils.isNotEmpty(params)) {
            for (int i = 0; i < params.length; i++) {
                Parameter param = params[i];
                Object arg = args[i];
                String paramName = param.getName();
                log.info(paramName + " = " + arg);
            }
        }
        Object res = null;
        try {
            res = method.invoke(this.target, args);
            log.info("<------返回" + targetClassName + "." + targetMethodName + "，返回值：" + res);
        } catch (Exception e) {
            log.error("<------异常" + targetClassName + "." + targetMethodName, e);
            throw e;
        }
        return res;
    }
}
