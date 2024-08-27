package com.tiny.spring.factory;

import com.tiny.spring.BeanException;

public interface InitializingBean {
    void afterPropertiesSet() throws BeanException;
}
