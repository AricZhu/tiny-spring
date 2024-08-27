package com.tiny.spring.factory.support;

import cn.hutool.core.util.StrUtil;
import com.tiny.spring.BeanException;
import com.tiny.spring.factory.DisposableBean;
import com.tiny.spring.factory.config.BeanDefinition;

import java.lang.reflect.Method;

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
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        // 这里确保方法名不是 destroy，防止重复调用
        if (StrUtil.isNotEmpty(this.destroyMethodName) && !("destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(this.destroyMethodName);
            if (destroyMethod == null) {
                throw new BeanException("could not found destroy method in bean " + beanName + " for method " + destroyMethodName);
            }

            destroyMethod.invoke(bean);
        }
    }
}
