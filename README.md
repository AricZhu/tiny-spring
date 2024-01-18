# tiny-spring
## Bean 容器
Bean 容器是为了 Spring 框架管理所有的对象，因为在 Spring 框架中使用 IOC（控制反转）技术来创建和管理所有的对象。因此很自然的就需要一个容器来管理这些对象了。

Bean 容器主要有两个类实现：BeanDefinition 和 BeanFactory。

* BeanDefinition：用来定义对象
* BeanFactory：使用 HashMap 来管理 BeanDefinition
