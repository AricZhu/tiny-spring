package com.spring.tiny.beans.factory;

public interface BeanNameAware extends Aware {
    void setBeanName(String name);
}
