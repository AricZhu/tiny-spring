package com.tiny.spring.factory;

import com.tiny.spring.BeanException;

public interface BeanFactory {
    Object getBean(String beanName) throws BeanException;
}
