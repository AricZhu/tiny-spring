package com.tiny.spring.factory.config;

import com.tiny.spring.BeanException;
import com.tiny.spring.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException;
}
