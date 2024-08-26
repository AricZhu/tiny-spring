package com.tiny.spring.factory;

import com.tiny.spring.BeanException;

public interface BeanFactory {
    Object getBean(String beanName) throws BeanException;

    Object getBean(String beanName, Object... args) throws BeanException;

    <T> T getBean(String name, Class<T> requiredType) throws BeanException;
}
