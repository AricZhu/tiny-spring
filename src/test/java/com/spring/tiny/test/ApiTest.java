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
        UserService userService = (UserService) beanFactory.getBean("userService", "小明");
        userService.queryUserInfo();
    }
}
