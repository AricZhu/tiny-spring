import bean.UserService;
import cn.hutool.core.io.IoUtil;
import com.tiny.spring.context.support.ClassPathXmlApplicationContext;
import com.tiny.spring.factory.core.io.DefaultResourceLoader;
import com.tiny.spring.factory.core.io.Resource;
import com.tiny.spring.factory.core.io.ResourceLoader;
import com.tiny.spring.factory.support.DefaultListableBeanFactory;
import com.tiny.spring.factory.xml.XmlBeanDefinitionReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class ApiTest {
    @Test
    public void test_xml() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("src/test/resources/spring.xml");
        applicationContext.registerShutdownHook();

        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();

        System.out.println("测试结果: " + result);
        System.out.println("application: " + userService.getApplicationContext());
        System.out.println("beanfactory: " + userService.getBeanFactory());
    }
}
