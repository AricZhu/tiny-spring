package com.tiny.spring.factory.support;

import com.tiny.spring.BeanException;
import com.tiny.spring.factory.DisposableBean;
import com.tiny.spring.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    protected static final Object NULL_OBJECT = new Object();

    private final Map<String, Object> singletonObjects = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    @Override
    public void destroySingletons() {
        Set<String> keySet = disposableBeans.keySet();

        String[] disposableBeanNames = keySet.toArray(new String[0]);
        for (int i = 0; i < disposableBeanNames.length; i++) {
            String disposableBeanName = disposableBeanNames[i];
            DisposableBean bean = disposableBeans.remove(disposableBeanName);
            try {
                bean.destroy();
            } catch (Exception e) {
                throw new BeanException("failed to destroy bean " + disposableBeanName, e);
            }
        }
    }
}
