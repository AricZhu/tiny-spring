package com.tiny.spring.factory.support;

import com.tiny.spring.BeanException;
import com.tiny.spring.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeanException {
        Class beanClass = beanDefinition.getBeanClass();
        Object beanInstance = null;

        try {
            if (ctor != null) {
                beanInstance = beanClass.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                beanInstance = beanClass.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            throw new BeanException("实例化失败: " + beanClass.getName(), e);
        }

        return beanInstance;
    }
}
