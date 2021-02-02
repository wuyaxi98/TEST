package com.ty.proxyTest.testtwo;
import org.apache.commons.lang3.ArrayUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class ReflectUtilsTest {

    public static void main(String[] args) {
        try {
            ReflectUtilsTest.reflectUtil(TransactionalInvocationHandler.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static OutputStreamWriter  reflectUtil(Class className) throws Exception {
        Method[] Methods = className.getDeclaredMethods();
        File f = new File("D:\\Sub.java");
        FileOutputStream fos = new FileOutputStream(f);
        OutputStreamWriter dos = new OutputStreamWriter(fos);

        dos.write("import "+className.getPackageName()+";"+"\r\n");
        dos.append("\r\n");

        dos.write("import org.slf4j.Logger;"+"\r\n");
        dos.write("import org.slf4j.LoggerFactory;"+"\r\n");
        dos.append("\r\n");

        dos.write("public class Sub extends "+ className.getSimpleName() +" {"+ "\r\n");
        dos.append("\r\n");
        dos.write("private static final Logger log = LoggerFactory.getLogger(LogInvocationHandler.class);");
        dos.append("\r\n");

        Field[] fields = className.getDeclaredFields();
        if (ArrayUtils.isNotEmpty(fields )) {
            for (Field field: fields) {
                dos.write(Modifier.toString(field.getModifiers())+" "+ field.getType().getSimpleName()+" "+field.getName()+";"+"\r\n");

            }
            dos.append("\r\n");

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
                dos.write(")" +" {"+"\r\n");;
                dos.write("log.info(开始执行"+ methodName+"方法);"+"\r\n");
                dos.write("super."+ methodName+"();"+"\r\n");
                dos.write("log.info("+methodName+"方法执行结束);"+"\r\n");
                dos.append("}"+"\r\n");
                dos.append("\r\n");

            }
            else {
                dos.write(")" +" {"+"\r\n");
                dos.write("log.info(开始执行"+ methodName+"方法);"+"\r\n");
                dos.write("super."+ methodName+"();"+"\r\n");
                dos.write("log.info("+methodName+"方法执行结束);"+"\r\n");
                dos.append("}"+"\r\n");
                dos.append("\r\n");


            }

        }
        dos.write("}");
        dos.close();
        return dos;

    }




}
