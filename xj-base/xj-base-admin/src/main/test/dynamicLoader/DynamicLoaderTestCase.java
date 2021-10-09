package dynamicLoader;

import io.github.wslxm.XijiaServer;
import io.github.wslxm.pay.core.common.utils.DynamicLoader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
/**
 * webEnvironment : 大致意思是：我们在测试使用 websocket的时候需要启动一个完整的服务器，而使用这个注解就是说每次测试都会选用一个随即可用的端口模拟启动一个完整的服务器，此时问题完美解决!!
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XijiaServer.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DynamicLoaderTestCase {
    private String javaSrc = "public class TestClass{" +
            "public void sayHello(String msg) {" +
            "System.out.printf(\"Hello %s! This message from a Java String.%n\",msg);" +
            "}" +
            "public int add(int a,int b){" +
            "return a+b;" +
            "}" +
            "}";
    @Test
    public void testCompile() {
        Map<String, byte[]> bytecode = DynamicLoader.compile("TestClass.java", javaSrc);
        for (Iterator<String> iterator = bytecode.keySet().iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            byte[] code = bytecode.get(key);
            System.out.printf("Class: %s, Length: %d%n", key, code.length);
        }
       // Since the compiler and compiler options are different, the size of the bytes may be inconsistent.
        Assert.assertEquals(558, bytecode.get("TestClass").length);
    }


    @Test
    public void testInvoke() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Random random = new Random();
        int a = random.nextInt(1024);
        int b = random.nextInt(1024);
        Map<String, byte[]> bytecode = DynamicLoader.compile("TestClass.java", javaSrc);
        DynamicLoader.MemoryClassLoader classLoader = new DynamicLoader.MemoryClassLoader(bytecode);
        Class clazz = classLoader.loadClass("TestClass");
        Object object = clazz.newInstance();
        Method method = clazz.getMethod("add", int.class, int.class);
        Object returnValue = method.invoke(object, a, b);
        Assert.assertEquals(a + b, returnValue);
    }
}