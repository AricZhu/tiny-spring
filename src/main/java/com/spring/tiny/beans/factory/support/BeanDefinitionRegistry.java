package com.spring.tiny.beans.factory.support;

import com.spring.tiny.beans.factory.factory.BeanDefinition;

public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    boolean containsBeanDefinition(String beanName);
}
