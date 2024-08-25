package com.tiny.spring.factory.support;

import com.tiny.spring.factory.core.io.Resource;
import com.tiny.spring.factory.core.io.ResourceLoader;

import java.io.IOException;

public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();
    ResourceLoader getResourceLoader();
    void loadBeanDefinitions(Resource resource) throws IOException;
    void loadBeanDefinitions(Resource... resources) throws IOException;
    void loadBeanDefinitions(String location) throws IOException;
}
