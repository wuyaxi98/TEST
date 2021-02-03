package Impl;

import com.ty.proxyTest.testtwo.utils.Constant;
import com.ty.proxyTest.testtwo.utils.Father;
import com.ty.proxyTest.testtwo.utils.GenerateClassTest;
import com.ty.proxyTest.testtwo.utils.ReflectUtil;
import java.io.OutputStreamWriter;

public class ReflectUtilsTest {
    public static void main(String[] args) throws Exception {
        try {
            ReflectUtilsTest.generateClass(GenerateClassTest.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public static OutputStreamWriter generateClass(Class fatherClassName) throws Exception {
        OutputStreamWriter dos = ReflectUtil.creatFile(fatherClassName);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ReflectUtil.addClass(fatherClassName));
        stringBuilder.append(ReflectUtil.addConstructors(fatherClassName));
        stringBuilder.append(ReflectUtil.addMethods(fatherClassName));
        dos.write(stringBuilder.toString());
        dos.write("}" + Constant.HUANHANG);
        dos.close();
        return dos;


    }


}
