package com.spring.tiny.test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private final static Map<String, String> hashMap = new HashMap<>();
    public void initDataMethod(){
        System.out.println("执行：init-method");
        hashMap.put("1001", "小傅哥");
        hashMap.put("1002", "八杯水");
        hashMap.put("1003", "阿毛");
    }

    public void destroyDataMethod(){
        System.out.println("执行：destroy-method");
        hashMap.clear();
    }

    public String queryUserName(String userId) {
        return hashMap.get(userId);
    }
}
