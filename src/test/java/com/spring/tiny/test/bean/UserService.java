package com.spring.tiny.test.bean;

public class UserService {
    private UserDao userDao;

    public void queryUserInfo(String userId) {
        System.out.println("查询用户信息：" + userDao.queryUserName(userId));
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
