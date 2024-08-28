package com.tiny.spring.factory;

import com.tiny.spring.BeanException;

public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory) throws BeanException;
}
