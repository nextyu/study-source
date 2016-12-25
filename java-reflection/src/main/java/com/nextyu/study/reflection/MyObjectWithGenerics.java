package com.nextyu.study.reflection;

import java.util.List;

/**
 * 2016-12-25 下午3:54
 *
 * @author nextyu
 */
public class MyObjectWithGenerics {
    public List<String> stringList;

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }
}
