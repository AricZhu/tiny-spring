package com.tiny.spring.context.support;

import com.tiny.spring.factory.support.DefaultListableBeanFactory;
import com.tiny.spring.factory.xml.XmlBeanDefinitionReader;


public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] locations = getConfigLocations();
        if (locations != null) {
            beanDefinitionReader.loadBeanDefinitions(locations);
        }

    }

    protected abstract String[] getConfigLocations();
}
