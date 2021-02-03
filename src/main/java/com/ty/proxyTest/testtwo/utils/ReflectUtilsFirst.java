package com.ty.proxyTest.testtwo.utils;
import com.ty.proxyTest.testtwo.log.TransactionalInvocationHandler;
import org.apache.commons.lang3.ArrayUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.*;

public class ReflectUtilsFirst {

    public static void main(String[] args) {
        try {
            ReflectUtilsFirst.reflectUtil(TransactionalInvocationHandler.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static OutputStreamWriter  reflectUtil(Class fatherName) throws Exception {
        String sunName = fatherName.getSimpleName()+"Impl";
        Method[] Methods = fatherName.getDeclaredMethods();
        File f = new File("D:\\"+sunName+".java");
        FileOutputStream fos = new FileOutputStream(f);
        OutputStreamWriter dos = new OutputStreamWriter(fos);

        dos.write("import "+ fatherName.getPackage().getName()+";"+ Constant.HUANHANG);
        dos.append(Constant.HUANHANG);

        dos.write("import org.slf4j.Logger;"+Constant.HUANHANG);
        dos.write("import org.slf4j.LoggerFactory;"+Constant.HUANHANG);
        dos.append(Constant.HUANHANG);

        dos.write("public class Sub extends "+ fatherName.getSimpleName() +" {"+ Constant.HUANHANG);
        dos.append(Constant.HUANHANG);
        dos.write("private static final Logger log = LoggerFactory.getLogger("+ fatherName.getSimpleName() +".class);");
        dos.append(Constant.HUANHANG);

        Field[] fields = fatherName.getDeclaredFields();
        if (ArrayUtils.isNotEmpty(fields )) {
            for (Field field: fields) {
                dos.write(Modifier.toString(field.getModifiers())+" "+ field.getType().getSimpleName()+" "+field.getName()+";"+Constant.HUANHANG);

            }
            dos.append(Constant.HUANHANG);

        }


        for (Method method:Methods) {
            method.setAccessible(true);
            System.out.println(method);
            String methodName = method.getName();
            Parameter[] params = method.getParameters();
            Class[] paramsTypes = method.getParameterTypes();
            String modifier = Modifier.toString(method.getModifiers());
            Class returnType = method.getReturnType();
            dos.write(modifier +" "+ returnType.getSimpleName() +" "+methodName +" (" );
            if (ArrayUtils.isNotEmpty(params)) {
                for (int i = 0; i < params.length-1; i++) {
                    Parameter param = params[i];
                    dos.write(paramsTypes[i].getSimpleName() + " " + param.getName() + ", ");

                }
                dos.write(paramsTypes[params.length-1].getSimpleName() + " " + params[params.length-1].getName() );
                dos.write(")" +" {"+Constant.HUANHANG);;
                dos.write("log.info(开始执行"+ methodName+"方法);"+Constant.HUANHANG);
                dos.write("super."+ methodName+"();"+Constant.HUANHANG);
                dos.write("log.info("+methodName+"方法执行结束);"+Constant.HUANHANG);
                dos.append("}"+Constant.HUANHANG);
                dos.append(Constant.HUANHANG);

            }
            else {
                dos.write(")" +" {"+Constant.HUANHANG);
                dos.write("log.info(开始执行"+ methodName+"方法);"+Constant.HUANHANG);
                dos.write("super."+ methodName+"();"+Constant.HUANHANG);
                dos.write("log.info("+methodName+"方法执行结束);"+Constant.HUANHANG);
                dos.append("}"+Constant.HUANHANG);
                dos.append(Constant.HUANHANG);


            }

        }
        dos.write(ReflectUtilsFirst.addConstructors(fatherName));
        dos.write("}");
        dos.close();

        return dos;

    }

    public static String addConstructors(Class fatherName)throws Exception{
        Constructor[]  constructors = fatherName.getDeclaredConstructors();
        StringBuilder stringBuilder = new StringBuilder();
        if (ArrayUtils.isNotEmpty(constructors)) {
            for (Constructor constructor:constructors) {
                constructor.setAccessible(true);
                System.out.println(constructor);
                Parameter[] params = constructor.getParameters();
                Class[] paramsTypes = constructor.getParameterTypes();
                String modifier = Modifier.toString(constructor.getModifiers());
                //Class returnType = constructor.();
                stringBuilder.append(modifier+" "+fatherName.getSimpleName()+"Impl(");
                if (ArrayUtils.isNotEmpty(params)) {
                    for (int i = 0; i < params.length-1; i++) {
                        Parameter param = params[i];
                        stringBuilder.append(paramsTypes[i].getSimpleName() + " " + param.getName() + ", ");

                    }
                    stringBuilder.append(paramsTypes[params.length-1].getSimpleName() + " " + params[params.length-1].getName() );
                    stringBuilder.append(")" +" {"+Constant.HUANHANG);
                    stringBuilder.append("super("+ params[params.length-1].getName()+");"+Constant.HUANHANG);
                }
                else {
                        stringBuilder.append(")" + " {" + Constant.HUANHANG);

                }
                stringBuilder.append("}"+Constant.HUANHANG);

            }




        }
        return stringBuilder.toString();
    }





}
