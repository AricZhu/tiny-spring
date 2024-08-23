package com.tiny.spring.factory.support;

import com.tiny.spring.BeanException;
import com.tiny.spring.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeanException;
}
