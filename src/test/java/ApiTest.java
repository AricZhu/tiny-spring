import bean.UserService;
import cn.hutool.core.io.IoUtil;
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
    private ResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }
    @Test
    public void test_ResourceLoader() throws IOException {
        Resource classRes = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = classRes.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);

        Resource fileRes = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStreamFile = fileRes.getInputStream();
        String contentFile = IoUtil.readUtf8(inputStreamFile);
        System.out.println(contentFile);


        Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/blob/main/small-spring-step-05/src/test/resources/important.properties");
        InputStream inputStreamRes = resource.getInputStream();
        String contentRes = IoUtil.readUtf8(inputStreamRes);
        System.out.println(contentRes);
    }

    @Test
    public void test_BeanDefinitionReader() throws IOException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("src/test/resources/spring.xml");

        UserService userService = (UserService)beanFactory.getBean("userService");

        userService.queryUserInfo();
    }
}
