import bean.UserService;
import cn.hutool.core.io.IoUtil;
import com.tiny.spring.context.support.ClassPathXmlApplicationContext;
import com.tiny.spring.factory.core.io.DefaultResourceLoader;
import com.tiny.spring.factory.core.io.Resource;
import com.tiny.spring.factory.core.io.ResourceLoader;
import com.tiny.spring.factory.support.DefaultListableBeanFactory;
import com.tiny.spring.factory.xml.XmlBeanDefinitionReader;
import event.CustomEvent;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class ApiTest {
    @Test
    public void test_xml() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));

        applicationContext.registerShutdownHook();
    }
}
