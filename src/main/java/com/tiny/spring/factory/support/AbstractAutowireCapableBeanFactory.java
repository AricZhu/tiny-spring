package com.tiny.spring.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.tiny.spring.BeanException;
import com.tiny.spring.factory.PropertyValue;
import com.tiny.spring.factory.PropertyValues;
import com.tiny.spring.factory.config.BeanDefinition;
import com.tiny.spring.factory.config.BeanReference;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    private InstantiationStrategy instantiationStrategy  = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);

            applyPropertyValues(beanName, bean, beanDefinition);
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

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        try {
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }

                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeanException("set property error: " + beanName, e);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
