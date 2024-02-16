package com.spring.tiny.beans.factory;

import com.spring.tiny.beans.BeansException;

public interface InitializingBean {

    /**
     * Bean 处理了属性填充后调用
     *
     * @throws Exception
     */
    void afterPropertiesSet() throws BeansException;
}
