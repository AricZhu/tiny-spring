package com.spring.tiny.test.bean;

import com.spring.tiny.beans.BeansException;
import com.spring.tiny.beans.factory.*;
import com.spring.tiny.context.ApplicationContext;

public class UserService implements InitializingBean, DisposableBean, BeanFactoryAware, BeanClassLoaderAware, BeanNameAware, ApplicationContextAware {
    private IUserDao userDao;
    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    private String company;
    private String location;

    public String queryUserInfo(String userId) {
        return userDao.queryUserName(userId) + "," + company + "," + location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws BeansException {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("Class loader: " + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean name is: " + name);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
}
