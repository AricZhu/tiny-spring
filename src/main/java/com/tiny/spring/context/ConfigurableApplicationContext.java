package com.tiny.spring.context;

import com.tiny.spring.BeanException;

public interface ConfigurableApplicationContext extends ApplicationContext {
    void refresh() throws BeanException;

    void registerShutdownHook();

    void close();
}
