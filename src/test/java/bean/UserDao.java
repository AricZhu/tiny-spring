package bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();


    public void initDataMethod(){
        System.out.println("执行：init-method");
        hashMap.put("1001", "小红");
        hashMap.put("1002", "小明");
        hashMap.put("1003", "小张");
        hashMap.put("1004", "小李");
    }

    public void destroyDataMethod(){
        System.out.println("执行：destroy-method");
        hashMap.clear();
    }

    public String queryUserName(String userId) {
        return hashMap.get(userId);
    }
}
