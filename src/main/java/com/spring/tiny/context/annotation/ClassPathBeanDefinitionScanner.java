package com.spring.tiny.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.spring.tiny.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.spring.tiny.beans.factory.factory.BeanDefinition;
import com.spring.tiny.beans.factory.support.BeanDefinitionRegistry;

import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Set;

public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {
    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidates) {
                String beanScope = resolveBeanScope(beanDefinition);
                if (StrUtil.isNotEmpty(beanScope)) {
                    beanDefinition.setScope(beanScope);
                }
                registry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }
        // 注册处理注解的 BeanPostProcessor（@Autowired、@Value）
        registry.registerBeanDefinition("com.spring.tiny.context.annotation.internalAutowiredAnnotationProcessor", new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
    }

    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class beanClass = beanDefinition.getBeanClass();
        Scope scope = (Scope) beanClass.getAnnotation(Scope.class);
        if (Objects.nonNull(scope)) {
            return scope.value();
        }

        return StrUtil.EMPTY;
    }

    private String determineBeanName(BeanDefinition beanDefinition) {
        Class beanClass = beanDefinition.getBeanClass();
        Component component = (Component)beanClass.getAnnotation(Component.class);
        if (Objects.nonNull(component) && StrUtil.isNotEmpty(component.value())) {
            return component.value();
        }
        return StrUtil.lowerFirst(beanClass.getSimpleName());
    }
}
