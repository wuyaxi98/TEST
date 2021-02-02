package Impl;

import com.ty.proxyTest.testtwo.Father;
import com.ty.proxyTest.testtwo.utils.Constant;
import com.ty.proxyTest.testtwo.utils.ReflectUtils;
import java.io.OutputStreamWriter;

public class ReflectUtilsTest {
    public static void main(String[] args) throws Exception {
        try {
            ReflectUtilsTest.generateClass(Father.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public static OutputStreamWriter generateClass(Class fatherClassName) throws Exception{
        OutputStreamWriter dos = ReflectUtils.CreatFile(fatherClassName);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ReflectUtils.addClass(fatherClassName));
        stringBuilder.append(ReflectUtils.addConstructors(fatherClassName));
        stringBuilder.append(ReflectUtils.addMethods(fatherClassName));
        dos.write(stringBuilder.toString());
        dos.write("}"+Constant.HUANHANG);
        dos.close();
        return dos;


    }


}
