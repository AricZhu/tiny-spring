package com.spring.tiny.beans.factory;

public interface DisposableBean {
    void destroy() throws Exception;
}
