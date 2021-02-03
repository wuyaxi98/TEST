package com.ty.proxyTest.testtwo.utils;

import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class ReflectUtil {
    public static OutputStreamWriter creatFile(Class fatherName) throws Exception {
        String sonName = fatherName.getSimpleName() + "Impl";
        File f = new File("D:\\" + sonName + ".java");
        FileOutputStream fos = new FileOutputStream(f);
        OutputStreamWriter dos = new OutputStreamWriter(fos);
        return dos;

    }


    public static String addClass(Class fatherName) throws Exception {
        String sonName = fatherName.getSimpleName() + "Impl";
        StringBuilder stringBuilder = new StringBuilder();
        //stringBuilder.append("package com.ty.proxyTest.testtwo;" + Constant.HUANHANG);
        stringBuilder.append("package  " + fatherName.getPackage().getName() + ";" + Constant.HUANHANG);
        stringBuilder.append(Constant.HUANHANG);
        //stringBuilder.append("import "+ fatherName.getPackage().getName()+";"+ Constant.HUANHANG);
        stringBuilder.append("import org.slf4j.Logger;" + Constant.HUANHANG);
        stringBuilder.append("import org.slf4j.LoggerFactory;" + Constant.HUANHANG);
        stringBuilder.append(Constant.HUANHANG);

        stringBuilder.append("public class " + sonName + " " + "extends " + fatherName.getSimpleName() + " {" + Constant.HUANHANG);
        stringBuilder.append(Constant.HUANHANG);
        stringBuilder.append("private static final Logger log = LoggerFactory.getLogger(" + sonName + ".class);");
        stringBuilder.append(Constant.HUANHANG);
        return stringBuilder.toString();
    }

    public static String addConstructors(Class fatherName) throws Exception {
        Constructor[] constructors = fatherName.getDeclaredConstructors();
        StringBuilder stringBuilder = new StringBuilder();
        if (ArrayUtils.isNotEmpty(constructors)) {
            for (Constructor constructor : constructors) {
                Parameter[] params = constructor.getParameters();
                Class[] paramsTypes = constructor.getParameterTypes();
                String modifier = Modifier.toString(constructor.getModifiers());
                stringBuilder.append(modifier + " " + fatherName.getSimpleName() + "Impl(");
                if (ArrayUtils.isNotEmpty(params)) {
                    for (int i = 0; i < params.length - 1; i++) {
                        Parameter param = params[i];
                        stringBuilder.append(paramsTypes[i].getSimpleName() + " " + param.getName() + ", ");

                    }
                    stringBuilder.append(paramsTypes[params.length - 1].getSimpleName() + " " + params[params.length - 1].getName());
                    stringBuilder.append(")" + " {" + Constant.HUANHANG);
                    stringBuilder.append("super(");
                    for (int i = 0; i < params.length - 1; i++) {
                        stringBuilder.append(params[i].getName() + ",");
                    }
                    stringBuilder.append(params[params.length - 1].getName() + ");" + Constant.HUANHANG);

                } else {
                    stringBuilder.append(")" + " {" + Constant.HUANHANG);

                }
                stringBuilder.append("}" + Constant.HUANHANG);

            }

        }
        return stringBuilder.toString();
    }


    public static String addMethods(Class fatherName) throws Exception {
        Method[] Methods = fatherName.getDeclaredMethods();
        StringBuilder stringBuilder = new StringBuilder();
        for (Method method : Methods) {
            String methodName = method.getName();
            Parameter[] params = method.getParameters();
            Class[] paramsTypes = method.getParameterTypes();
            String modifier = Modifier.toString(method.getModifiers());
            Class returnType = method.getReturnType();
            stringBuilder.append(modifier + " " + returnType.getName() + " " + methodName + " (");
            if (ArrayUtils.isNotEmpty(params)) {
                for (int i = 0; i < params.length - 1; i++) {
                    Parameter param = params[i];
                    stringBuilder.append(paramsTypes[i].getSimpleName() + " " + param.getName() + ", ");

                }
                stringBuilder.append(paramsTypes[params.length - 1].getSimpleName() + " " + params[params.length - 1].getName());
                stringBuilder.append(")" + " {" + Constant.HUANHANG);

                stringBuilder.append("log.info(\"开始执行有参" + methodName + "方法\");" + Constant.HUANHANG);
                String returnTypeName = method.getReturnType().getName();
                if (method.getReturnType()!=void.class) {
                    stringBuilder.append(returnTypeName+" result =");


                }
                stringBuilder.append("super." + methodName + "(");
                for (int i = 0; i < params.length - 1; i++) {
                    stringBuilder.append(params[i].getName() + ",");
                }
                stringBuilder.append(params[params.length - 1].getName() + ");" + Constant.HUANHANG);
                stringBuilder.append("log.info(\"" + methodName + "有参方法执行结束\");" + Constant.HUANHANG);
                if (method.getReturnType()!=void.class) {
                    stringBuilder.append("return  result;");


                }
                stringBuilder.append("}" + Constant.HUANHANG);
                stringBuilder.append(Constant.HUANHANG);

            } else {


                stringBuilder.append(")" + " {" + Constant.HUANHANG);
                stringBuilder.append("log.info(\"开始执行无参" + methodName + "方法\");" + Constant.HUANHANG);
                String returnTypeName = method.getReturnType().getName();
                if (method.getReturnType()!=void.class) {
                    stringBuilder.append(returnTypeName+" result =");


                }
                stringBuilder.append("super." + methodName + "();" + Constant.HUANHANG);
                stringBuilder.append("log.info(\"" + methodName + "无参方法执行结束\");" + Constant.HUANHANG);
                if (method.getReturnType()!=void.class) {
                    stringBuilder.append("return  result;" + Constant.HUANHANG);


                }
                stringBuilder.append("}" + Constant.HUANHANG);
                stringBuilder.append(Constant.HUANHANG);
            }

        }

        return stringBuilder.toString();
    }
}
