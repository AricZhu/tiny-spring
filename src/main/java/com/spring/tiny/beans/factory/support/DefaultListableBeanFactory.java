package com.spring.tiny.beans.factory.support;

import com.spring.tiny.beans.BeansException;
import com.spring.tiny.beans.factory.factory.BeanDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {
    private Map<String, BeanDefinition> beanDefinitionsMap = new HashMap<String, BeanDefinition>();

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionsMap.get(beanName);
        if (Objects.isNull(beanDefinition)) {
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }

        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionsMap.put(beanName, beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionsMap.containsKey(beanName);
    }
}
