package com.tiny.spring.factory.support;

import com.tiny.spring.BeanException;
import com.tiny.spring.factory.FactoryBean;

import java.util.HashMap;
import java.util.Map;

public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {
    private final Map<String, Object> factoryBeanObjectCache = new HashMap<>();

    protected Object getCachedObjectForFactoryBean(String beanName) {
        Object bean = this.factoryBeanObjectCache.get(beanName);
        return bean != NULL_OBJECT ? bean : null;
    }

    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName) {
        // 单例模式时需要保存到 cache 中
        if (factory.isSingleton()) {
            Object bean = getCachedObjectForFactoryBean(beanName);
            if (bean == null) {
                bean = doGetObjectFromFactoryBean(factory, beanName);
                this.factoryBeanObjectCache.put(beanName, bean != null ? bean : NULL_OBJECT);
            }

            return bean != NULL_OBJECT ? bean : null;
        }

        return doGetObjectFromFactoryBean(factory, beanName);
    }

    protected Object doGetObjectFromFactoryBean(FactoryBean factory, String beanName) {
        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new BeanException("FactoryBean threw exception on object[" + beanName + "] creation", e);
        }
    }
}
