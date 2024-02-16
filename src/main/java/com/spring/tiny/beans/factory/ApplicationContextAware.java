package com.spring.tiny.beans.factory;

import com.spring.tiny.beans.BeansException;
import com.spring.tiny.context.ApplicationContext;

public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
