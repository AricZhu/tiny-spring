package com.tiny.spring.context.support;

import com.tiny.spring.BeanException;
import com.tiny.spring.factory.ConfigurableListableBeanFactory;
import com.tiny.spring.factory.support.DefaultListableBeanFactory;

public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeanException {
        DefaultListableBeanFactory beanFactory1 = createBeanFactory();
        loadBeanDefinitions(beanFactory1);
        this.beanFactory = beanFactory1;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return this.beanFactory;
    }
}
