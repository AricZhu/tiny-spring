package com.tiny.spring.factory.config;

import com.tiny.spring.BeanException;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeanException;
}
