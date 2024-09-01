package com.tiny.spring;

import com.tiny.spring.aop.Advisor;
import com.tiny.spring.aop.Pointcut;

public interface PointcutAdvisor extends Advisor {
    Pointcut getPointcut();
}
