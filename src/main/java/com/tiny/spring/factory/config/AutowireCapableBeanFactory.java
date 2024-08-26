package com.tiny.spring.factory.config;

import com.tiny.spring.BeanException;
import com.tiny.spring.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {
    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeanException;

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeanException;

}
