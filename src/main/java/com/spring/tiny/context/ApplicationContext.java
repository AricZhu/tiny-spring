package com.spring.tiny.context;

import com.spring.tiny.beans.factory.HierarchicalBeanFactory;
import com.spring.tiny.beans.factory.ListableBeanFactory;
import com.spring.tiny.core.io.ResourceLoader;

public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
