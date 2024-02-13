package com.spring.tiny.test.bean;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void test_args(Object... args) {
        System.out.println(Arrays.toString(args));
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Person person = new Person("张三", 18);
        person.test_args();
        person.test_args("1", "2", "3");
//        Constructor<?>[] constructors = Person.class.getConstructors();
//        for (Constructor<?> constructor : constructors) {
//            Class<?>[] parameterTypes = constructor.getParameterTypes();
//            for (Class<?> parameterType : parameterTypes) {
//                System.out.println(parameterType.getName());
//            }
//        }
//
//        Constructor<Person> constructor = Person.class.getConstructor(String.class, int.class);
//        Person person = constructor.newInstance("张三", 18);
//        System.out.println(person);
    }
}
