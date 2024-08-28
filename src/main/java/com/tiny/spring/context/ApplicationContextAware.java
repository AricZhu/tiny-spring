package com.tiny.spring.context;

import com.tiny.spring.BeanException;
import com.tiny.spring.factory.Aware;

public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeanException;
}
