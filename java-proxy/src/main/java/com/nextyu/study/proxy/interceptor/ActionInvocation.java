package com.nextyu.study.proxy.interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nextyu
 * @version 1.0
 */
public class ActionInvocation {
    private List<MyInterceptor> interceptors = new ArrayList<>();
    private int index = -1;
    private Action action = new Action();

    public ActionInvocation() {
        interceptors.add(new FirstInterceptor());
        interceptors.add(new SecondInterceptor());
        interceptors.add(new ThirdInterceptor());
    }

    public void invoke() {
        if (index + 1 >= interceptors.size()) {
            action.execute();
        } else {
            index++;
            interceptors.get(index).interceptor(this);
        }
    }
}
