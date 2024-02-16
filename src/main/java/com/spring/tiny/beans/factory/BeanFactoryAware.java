package com.spring.tiny.beans.factory;

import com.spring.tiny.beans.BeansException;

public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
