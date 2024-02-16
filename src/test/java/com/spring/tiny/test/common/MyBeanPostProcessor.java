package com.spring.tiny.test.common;

import com.spring.tiny.beans.BeansException;
import com.spring.tiny.beans.factory.factory.BeanPostProcessor;
import com.spring.tiny.test.bean.UserService;

public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}