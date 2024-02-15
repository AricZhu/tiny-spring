package com.spring.tiny.test.common;

import com.spring.tiny.beans.BeansException;
import com.spring.tiny.beans.factory.ConfigurableListableBeanFactory;
import com.spring.tiny.beans.factory.PropertyValue;
import com.spring.tiny.beans.factory.PropertyValues;
import com.spring.tiny.beans.factory.factory.BeanDefinition;
import com.spring.tiny.beans.factory.factory.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }

}