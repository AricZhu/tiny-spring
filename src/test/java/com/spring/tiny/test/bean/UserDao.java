package com.spring.tiny.test.bean;

import com.spring.tiny.context.annotation.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("1001", "小傅哥，北京，亦庄");
        hashMap.put("1002", "八杯水，上海，尖沙咀");
        hashMap.put("1003", "阿毛，香港，铜锣湾");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}