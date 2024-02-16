package com.spring.tiny.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.spring.tiny.beans.BeansException;
import com.spring.tiny.beans.factory.DisposableBean;
import com.spring.tiny.beans.factory.factory.BeanDefinition;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 通过适配器进行统一销毁
 */
public class DisposableBeanAdapter implements DisposableBean {
    private final Object bean;

    private final String beanName;

    private final String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // 1. 通过接口实现销毁钩子
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        // 2. 通过 xml 配置 destroy-method 实现销毁钩子
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (Objects.isNull(destroyMethod)) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
