package com.spring.tiny.test;

import com.spring.tiny.aop.AdvisedSupport;
import com.spring.tiny.aop.TargetSource;
import com.spring.tiny.aop.aspectj.AspectJExpressionPointcut;
import com.spring.tiny.context.support.ClassPathXmlApplicationContext;
import com.spring.tiny.core.io.DefaultResourceLoader;
import com.spring.tiny.test.bean.IUserService;
import com.spring.tiny.test.bean.UserService;
import com.spring.tiny.test.bean.UserServiceInterceptor;
import org.junit.Before;
import org.junit.Test;

public class ApiTest {
    private DefaultResourceLoader resourceLoader;

    private AdvisedSupport advisedSupport;

    @Before
    public void init() {

        resourceLoader = new DefaultResourceLoader();
        IUserService userService = new UserService();

        advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.spring.tiny.test.bean.IUserService.*(..))"));
    }

    @Test
    public void test_aop() throws NoSuchMethodException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }
}
