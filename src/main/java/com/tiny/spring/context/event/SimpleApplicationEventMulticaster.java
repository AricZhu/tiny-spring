package com.tiny.spring.context.event;

import com.tiny.spring.context.ApplicationEvent;
import com.tiny.spring.context.ApplicationListener;
import com.tiny.spring.factory.BeanFactory;

import java.util.Collection;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{
    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        Collection<ApplicationListener> applicationListeners = getApplicationListeners(event);
        for (ApplicationListener applicationListener : applicationListeners) {
            applicationListener.onApplicationEvent(event);
        }
    }
}
