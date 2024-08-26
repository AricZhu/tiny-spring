package com.tiny.spring.factory;

import com.tiny.spring.BeanException;
import com.tiny.spring.factory.config.AutowireCapableBeanFactory;
import com.tiny.spring.factory.config.BeanDefinition;
import com.tiny.spring.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, ConfigurableBeanFactory, AutowireCapableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    void preInstantiateSingletons() throws BeanException;
}
