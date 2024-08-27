package com.tiny.spring.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.tiny.spring.BeanException;
import com.tiny.spring.factory.PropertyValue;
import com.tiny.spring.factory.config.BeanDefinition;
import com.tiny.spring.factory.config.BeanReference;
import com.tiny.spring.factory.core.io.Resource;
import com.tiny.spring.factory.core.io.ResourceLoader;
import com.tiny.spring.factory.support.AbstractBeanDefinitionReader;
import com.tiny.spring.factory.support.BeanDefinitionRegistry;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeanException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BeanException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeanException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeanException {
        Resource resource = getResourceLoader().getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... location) throws BeanException {
        for (String s : location) {
            loadBeanDefinitions(s);
        }
    }

    protected void doBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();

        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (!(childNodes.item(i) instanceof Element)) {
                continue;
            }
            if (!"bean".equals(childNodes.item(i).getNodeName())) {
                continue;
            }

            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            String initMethod = bean.getAttribute("init-method");
            String destroyMethod = bean.getAttribute("destroy-method");
            Class<?> clazz = Class.forName(className);

            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmptyIfStr(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            BeanDefinition beanDefinition = new BeanDefinition(clazz);

            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethod);

            // 读取属性并填充
            NodeList propertyNodeList = bean.getChildNodes();
            for (int j = 0; j < propertyNodeList.getLength(); j++) {
                if (!(propertyNodeList.item(j) instanceof Element)) {
                    continue;
                }
                if (!"property".equals(propertyNodeList.item(j).getNodeName())) {
                    continue;
                }

                Element property = (Element) propertyNodeList.item(j);
                Object propertyValue = null;
                String propertyName = property.getAttribute("name");
                propertyValue = property.getAttribute("value");
                String ref = property.getAttribute("ref");

                if (StrUtil.isNotEmpty(ref)) {
                    propertyValue = new BeanReference(ref);
                }

                beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(propertyName, propertyValue));
            }

            BeanDefinitionRegistry registry = getRegistry();
            if (registry.containsBeanDefinition(beanName)) {
                throw new BeanException("Duplicate beanName [" + beanName + "] is not allowed.");
            }
            registry.registerBeanDefinition(beanName, beanDefinition);
        }
    }
}
