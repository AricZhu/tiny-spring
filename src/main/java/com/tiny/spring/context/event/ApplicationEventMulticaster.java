package com.tiny.spring.context.event;

import com.tiny.spring.context.ApplicationEvent;
import com.tiny.spring.context.ApplicationListener;

public interface ApplicationEventMulticaster {
    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);
}
