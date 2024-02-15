package com.spring.tiny.test;

import cn.hutool.core.io.IoUtil;
import com.spring.tiny.beans.factory.PropertyValue;
import com.spring.tiny.beans.factory.PropertyValues;
import com.spring.tiny.beans.factory.factory.BeanDefinition;
import com.spring.tiny.beans.factory.factory.BeanReference;
import com.spring.tiny.beans.factory.support.DefaultListableBeanFactory;
import com.spring.tiny.beans.factory.xml.XmlBeanDefinitionReader;
import com.spring.tiny.core.io.DefaultResourceLoader;
import com.spring.tiny.core.io.Resource;
import com.spring.tiny.test.bean.UserDao;
import com.spring.tiny.test.bean.UserService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class ApiTest {
    private DefaultResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println("test classpath: " + content);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println("test file: " + content);
    }

    @Test
    public void test_url() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println("test url: " + content);
    }

    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. UserDao 注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // 4. UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 5. UserService 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo("1001");
    }

    @Test
    public void test_xml() {
        // 1. 初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件 xml
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. 获取 Bean 对象
        UserService userService = (UserService)beanFactory.getBean("userService", UserService.class);

        userService.queryUserInfo("1003");
    }
}
