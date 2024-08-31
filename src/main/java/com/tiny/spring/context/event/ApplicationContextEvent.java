package com.tiny.spring.context.event;

import com.tiny.spring.context.ApplicationContext;
import com.tiny.spring.context.ApplicationEvent;

public class ApplicationContextEvent extends ApplicationEvent {
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
