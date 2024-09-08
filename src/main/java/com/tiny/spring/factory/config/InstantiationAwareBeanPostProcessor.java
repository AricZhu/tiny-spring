package com.tiny.spring.factory.config;

import com.tiny.spring.BeanException;
import com.tiny.spring.factory.PropertyValues;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeanException;
    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeanException;

}
