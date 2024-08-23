import bean.UserService;
import com.tiny.spring.factory.config.BeanDefinition;
import com.tiny.spring.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

public class ApiTest {
    @Test
    public void test_BeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);

        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService)beanFactory.getBean("userService", "小明");
        userService.queryUserInfo();

        UserService userServiceSingleton = (UserService) beanFactory.getBean("userService");
        userServiceSingleton.queryUserInfo();

        System.out.println("singleton: " + String.valueOf(userService == userServiceSingleton));
    }
}
