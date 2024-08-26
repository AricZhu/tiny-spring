package common;

import com.tiny.spring.BeanException;
import com.tiny.spring.factory.ConfigurableListableBeanFactory;
import com.tiny.spring.factory.PropertyValue;
import com.tiny.spring.factory.PropertyValues;
import com.tiny.spring.factory.config.BeanDefinition;
import com.tiny.spring.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }
}
