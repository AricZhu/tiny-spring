package com.spring.tiny.context.event;

import com.spring.tiny.context.ApplicationContext;
import com.spring.tiny.context.ApplicationEvent;

public class ApplicationContextEvent extends ApplicationEvent {
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
