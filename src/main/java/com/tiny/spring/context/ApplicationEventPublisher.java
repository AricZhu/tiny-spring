package com.tiny.spring.context;

public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
