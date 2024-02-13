# 手写 Spring 框架

本项目是在学习教程《Spring 手撸专栏》的笔记记录。教程原文链接如下：

[《Spring 手撸专栏》](https://mp.weixin.qq.com/s?__biz=MzIxMDAwMDAxMw==&mid=2650730541&idx=1&sn=9fcd5baf6ec3e880786c4a0384166bdd&chksm=8f6111cfb81698d9bb5a4c61075d87658f7296bdb42ea72dd1b7d4312f3b75f719399ed2223c&cur_album_id=1871634116341743621&scene=189#wechat_redirect)

## Bean 容器
Bean 容器就是 Spring 框架中管理所有对象的容器，因为在 Spring 框架中使用 IOC（控制反转）技术来创建和管理所有的对象，所以需要一个容器来管理这些对象。

Bean 容器主要有两个类实现：BeanDefinition 和 BeanFactory。

* BeanDefinition：用来定义对象
* BeanFactory：使用 HashMap 来管理 BeanDefinition

![容器](./document/img/img01.png)

### 完善 Bean 容器的设计
上面完成了一个基本的 Bean 容器，现在我们需要运用设计模式来完善 Bean 容器，使其具有单例模式的能力，职责分离，便于后期的管理：

整体的设计如下：

![容器](./document/img/img02.png)

容器中的各个类关系如下：

![继承关系](./document/img/img03.png)

