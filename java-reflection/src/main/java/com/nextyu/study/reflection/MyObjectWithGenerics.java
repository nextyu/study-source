package com.nextyu.study.reflection;

import java.util.List;

/**
 * 2016-12-25 下午3:54
 *
 * @author nextyu
 */
public class MyObjectWithGenerics {
    /**
     * 泛型成员变量
     */
    public List<String> stringList;

    /**
     * 泛型方法返回值
     *
     * @return
     */
    public List<String> getStringList() {
        return stringList;
    }

    /**
     * 泛型方法参数
     *
     * @param stringList
     */
    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }
}
