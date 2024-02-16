package com.spring.tiny.test;

import cn.hutool.core.io.IoUtil;
import com.spring.tiny.aop.AdvisedSupport;
import com.spring.tiny.aop.MethodMatcher;
import com.spring.tiny.aop.TargetSource;
import com.spring.tiny.aop.aspectj.AspectJExpressionPointcut;
import com.spring.tiny.aop.framework.Cglib2AopProxy;
import com.spring.tiny.aop.framework.JdkDynamicAopProxy;
import com.spring.tiny.beans.factory.PropertyValue;
import com.spring.tiny.beans.factory.PropertyValues;
import com.spring.tiny.beans.factory.factory.BeanDefinition;
import com.spring.tiny.beans.factory.factory.BeanReference;
import com.spring.tiny.beans.factory.support.DefaultListableBeanFactory;
import com.spring.tiny.context.support.ClassPathXmlApplicationContext;
import com.spring.tiny.core.io.DefaultResourceLoader;
import com.spring.tiny.core.io.Resource;
import com.spring.tiny.test.bean.IUserService;
import com.spring.tiny.test.bean.UserDao;
import com.spring.tiny.test.bean.UserService;
import com.spring.tiny.test.bean.UserServiceInterceptor;
import com.spring.tiny.test.event.CustomEvent;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
        String result = userService.queryUserInfo();
        System.out.println(result);

    }

    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService01 = applicationContext.getBean("userService", UserService.class);
        UserService userService02 = applicationContext.getBean("userService", UserService.class);
        System.out.println("scope 测试");
        System.out.println(userService01);
        System.out.println(userService02);

        String result = userService01.queryUserInfo();
        System.out.println(result);

    }

    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));

        applicationContext.registerShutdownHook();
    }

    @Test
    public void test_proxy_method() {
        // 目标对象(可以替换成任何的目标对象)
//        Object targetObj = new UserService();
//
//        // AOP 代理
//        IUserService proxy = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {
//            // 方法匹配器
//            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.spring.tiny.test.bean.IUserService.*(..))");
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                if (methodMatcher.matches(method, targetObj.getClass())) {
//                    // 方法拦截器
//                    MethodInterceptor methodInterceptor = invocation -> {
//                        long start = System.currentTimeMillis();
//                        try {
//                            return invocation.proceed();
//                        } finally {
//                            System.out.println("监控 - Begin By AOP");
//                            System.out.println("方法名称：" + invocation.getMethod().getName());
//                            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
//                            System.out.println("监控 - End\r\n");
//                        }
//                    };
//                    // 反射调用
//                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));
//                }
//                return method.invoke(targetObj, args);
//            }
//        });
//        String result = proxy.queryUserInfo();
//        System.out.println("测试结果：" + result);
    }

    @Test
    public void test_dynamic() {
        // 目标对象
        IUserService userService = new UserService();
        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.spring.tiny.test.bean.IUserService.*(..))"));

        // 代理对象(JdkDynamicAopProxy)
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_jdk.queryUserInfo());

        // 代理对象(Cglib2AopProxy)
        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_cglib.register("花花"));
    }

    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.spring.tiny.test.bean.UserService.*(..))");
        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));

        // true、true
    }
}
