package com.ty.proxyTest.testtwo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateClassTestImpl extends GenerateClassTest {

private static final Logger log = LoggerFactory.getLogger(GenerateClassTestImpl.class);
public GenerateClassTestImpl(String name) {
super(name);
}
public void name (String name) {
log.info("开始执行有参name方法");
super.name(name);
log.info("name有参方法执行结束");
}

public String name () {
log.info("开始执行无参name方法");
String result =super.name();
log.info("name无参方法执行结束");
return  result;}

public String toString () {
log.info("开始执行无参toString方法");
String result =super.toString();
log.info("toString无参方法执行结束");
return  result;}

public java.util.List toList () {
log.info("开始执行无参toList方法");
java.util.List result =super.toList();
log.info("toList无参方法执行结束");
return  result;}

}
