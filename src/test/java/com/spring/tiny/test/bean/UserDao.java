package com.spring.tiny.test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private final static Map<String, String> map = new HashMap<>();

    static {
        map.put("1001", "小明哥");
        map.put("1002", "小红哥");
        map.put("1003", "小李子");
    }

    public String queryUserName(String userId) {
        return map.get(userId);
    }
}
