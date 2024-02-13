package com.spring.tiny.beans.factory;

import com.spring.tiny.beans.BeansException;

public interface BeanFactory {
    Object getBean(String beanName) throws BeansException;
}
