package com.spring.tiny.beans.factory.support;

import com.spring.tiny.beans.BeansException;
import com.spring.tiny.beans.factory.BeanFactory;
import com.spring.tiny.beans.factory.factory.BeanDefinition;

import java.util.Objects;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        Object bean = getSingleton(beanName);
        if (Objects.nonNull(bean)) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition, args);
    }

    public abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    public abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
