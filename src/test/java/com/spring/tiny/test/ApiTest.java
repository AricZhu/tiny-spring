package com.spring.tiny.test;

import com.spring.tiny.BeanDefinition;
import com.spring.tiny.BeanFactory;
import com.spring.tiny.test.bean.UserService;
import org.junit.Test;

public class ApiTest {
    @Test
    public void test_BeanFactory() {
        BeanFactory beanFactory = new BeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(new UserService());

        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService)beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
