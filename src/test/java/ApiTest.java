import bean.UserDao;
import bean.UserService;
import com.tiny.spring.factory.PropertyValue;
import com.tiny.spring.factory.PropertyValues;
import com.tiny.spring.factory.config.BeanDefinition;
import com.tiny.spring.factory.config.BeanReference;
import com.tiny.spring.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

public class ApiTest {
    @Test
    public void test_BeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition userDaoBean = new BeanDefinition(UserDao.class);
        beanFactory.registerBeanDefinition("userDao", userDaoBean);

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "1002"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService)beanFactory.getBean("userService");

        userService.queryUserInfo();
    }
}
