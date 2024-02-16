package com.spring.tiny.test.bean;

import com.spring.tiny.beans.BeansException;
import com.spring.tiny.beans.factory.DisposableBean;
import com.spring.tiny.beans.factory.InitializingBean;

public class UserService implements InitializingBean, DisposableBean {
    private UserDao userDao;

    private String company;
    private String location;

    public String queryUserInfo(String userId) {
        return userDao.queryUserName(userId) + "," + company + "," + location;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws BeansException {
        System.out.println("执行：UserService.afterPropertiesSet");
    }
}
