package com.spring.tiny.beans.factory.support;

import com.spring.tiny.beans.BeansException;
import com.spring.tiny.beans.factory.factory.BeanDefinition;

import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * JDK 方式实例化（反射）
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Class beanClass = beanDefinition.getBeanClass();
        try {
            if (Objects.nonNull(ctor)) {
                return beanClass.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }
            return beanClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new BeansException("Failed to instantiate [" + beanClass.getName() + "]", e);
        }
    }
}
