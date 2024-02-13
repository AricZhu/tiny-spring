package com.spring.tiny.beans.factory.support;

import com.spring.tiny.beans.BeansException;
import com.spring.tiny.beans.factory.factory.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;
}
