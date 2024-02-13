package com.spring.tiny.beans.factory.support;

import com.spring.tiny.beans.factory.factory.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private Map<String, Object> singletonMap = new HashMap<String, Object>();
    @Override
    public Object getSingleton(String beanName) {
        return singletonMap.get(beanName);
    }

    public void addSingleton(String beanName, Object singletonObj) {
        singletonMap.put(beanName, singletonObj);
    }
}
