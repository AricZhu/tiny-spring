package com.tiny.spring.factory;

import com.tiny.spring.BeanException;

public interface FactoryBean<T> {
    T getObject() throws BeanException;

    Class<?> getObjectType();

    boolean isSingleton();
}
