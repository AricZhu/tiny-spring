import com.tiny.spring.BeanDefinition;
import com.tiny.spring.BeanFactory;
import org.junit.Test;

public class ApiTest {

    @Test
    public void testBeanFactory() {
        BeanFactory beanFactory = new BeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(new UserService());

        // 注册一个 Bean 对象
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 获取 Bean 对象
        UserService userService = (UserService)beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
