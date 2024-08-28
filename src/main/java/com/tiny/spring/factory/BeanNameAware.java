package com.tiny.spring.factory;

public interface BeanNameAware extends Aware {
    void setBeanName(String beanName);
}
