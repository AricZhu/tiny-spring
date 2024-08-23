package com.tiny.spring.factory.support;

import com.tiny.spring.BeanException;
import com.tiny.spring.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    private final InstantiationStrategy instantiationStrategy  = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
        } catch (Exception e) {
            throw new BeanException("createBean 错误");
        }

        addSingleton(beanName, bean);

        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Class<?> clzz = beanDefinition.getBeanClass();

        Constructor<?> defaultCtor = null;

        Constructor<?>[] ctorList = clzz.getDeclaredConstructors();

        for (Constructor<?> ctor : ctorList) {
            if (args != null && ctor.getParameterTypes().length == args.length) {
                defaultCtor = ctor;
                break;
            }
        }

        return getInstantiationStrategy().instantiate(beanDefinition, beanName, defaultCtor, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }
}
