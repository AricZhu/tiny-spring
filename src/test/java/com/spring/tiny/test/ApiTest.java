package com.spring.tiny.test;

import com.spring.tiny.beans.factory.PropertyValue;
import com.spring.tiny.beans.factory.PropertyValues;
import com.spring.tiny.beans.factory.factory.BeanDefinition;
import com.spring.tiny.beans.factory.factory.BeanReference;
import com.spring.tiny.beans.factory.support.DefaultListableBeanFactory;
import com.spring.tiny.test.bean.UserDao;
import com.spring.tiny.test.bean.UserService;
import org.junit.Test;

public class ApiTest {
    @Test
    public void test_BeanFactory() {
        // 1. 初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 注册 Bean
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class, propertyValues));

        // 3. 获取 Bean
        UserService userService = (UserService)beanFactory.getBean("userService");

        userService.queryUserInfo("1003");
    }
}
