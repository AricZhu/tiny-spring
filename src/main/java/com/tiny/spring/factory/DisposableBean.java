package com.tiny.spring.factory;

import com.tiny.spring.BeanException;

public interface DisposableBean {
    void destroy() throws Exception;
}
