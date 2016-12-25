package com.nextyu.study.reflection;

/**
 * 2016-12-25 下午2:00
 *
 * @author nextyu
 */
@MyAnnotation(name = "小灰灰", value = "喜洋洋")
public class MyObject {
    private String name;
    private Integer age;

    public MyObject() {
    }

    public MyObject(String name) {
        this.name = name;
    }

    public MyObject(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "MyObject{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    private String privateMethod1() {
        return "privateMethod1";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
