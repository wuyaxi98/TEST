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
    public static OutputStreamWriter generateClass(Class fathername) throws Exception{
        OutputStreamWriter dos = ReflectUtils.CreatFile(fathername);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ReflectUtils.addClass(fathername));
        stringBuilder.append(ReflectUtils.addConstructors(fathername));
        stringBuilder.append(ReflectUtils.addMethods(fathername));
        dos.write(stringBuilder.toString());
        dos.write("}"+Constant.HUANHANG);
        dos.close();
        return dos;


    }


}
