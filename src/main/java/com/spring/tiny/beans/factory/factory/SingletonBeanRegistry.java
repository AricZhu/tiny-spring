package com.spring.tiny.beans.factory.factory;

public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);

    void destroySingletons();
    void registerSingleton(String beanName, Object singletonObject);
}
