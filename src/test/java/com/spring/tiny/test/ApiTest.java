package com.spring.tiny.test;

import com.spring.tiny.beans.factory.factory.BeanDefinition;
import com.spring.tiny.beans.factory.support.DefaultListableBeanFactory;
import com.spring.tiny.test.bean.UserService;
import org.junit.Test;

public class ApiTest {
    @Test
    public void test_BeanFactory() {
        // 1. 初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 注册 BeanDefinition
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3. 第一次获取 Bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        // 4. 第二次获取 Bean
        UserService userService2 = (UserService) beanFactory.getBean("userService");
        userService2.queryUserInfo();
    }
}
