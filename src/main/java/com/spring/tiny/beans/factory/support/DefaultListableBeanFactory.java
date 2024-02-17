package com.spring.tiny.beans.factory.support;

import com.spring.tiny.beans.BeansException;
import com.spring.tiny.beans.factory.ConfigurableListableBeanFactory;
import com.spring.tiny.beans.factory.factory.BeanDefinition;
import com.spring.tiny.beans.factory.factory.BeanPostProcessor;

import java.util.*;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {
    private Map<String, BeanDefinition> beanDefinitionsMap = new HashMap<String, BeanDefinition>();

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionsMap.get(beanName);
        if (Objects.isNull(beanDefinition)) {
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }

        return beanDefinition;
    }

    public <T> T getBean(Class<T> requiredType) throws BeansException {
        List<String> beanNames = new ArrayList<>();
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionsMap.entrySet()) {
            Class beanClass = entry.getValue().getBeanClass();
            if (requiredType.isAssignableFrom(beanClass)) {
                beanNames.add(entry.getKey());
            }
        }
        if (1 == beanNames.size()) {
            return getBean(beanNames.get(0), requiredType);
        }

        throw new BeansException(requiredType + "expected single bean but found " + beanNames.size() + ": " + beanNames);
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionsMap.put(beanName, beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionsMap.containsKey(beanName);
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {
        beanDefinitionsMap.keySet().forEach(this::getBean);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionsMap.forEach((beanName, beanDefinition) -> {
            Class beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionsMap.keySet().toArray(new String[0]);
    }
}
