package bean;

import java.lang.reflect.Constructor;

public class UserService {
    private String name;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息: " + name);
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Class<UserService> clzz = UserService.class;
        Constructor<UserService> declaredConstructor = clzz.getDeclaredConstructor(String.class);
        System.out.println(declaredConstructor);
    }
}
