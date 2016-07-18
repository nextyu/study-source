package com.nextyu.study.proxy.service.impl;


import com.nextyu.study.proxy.domain.User;
import com.nextyu.study.proxy.service.OtherService;
import com.nextyu.study.proxy.service.UserService;

/**
 * @author nextyu
 * @version 1.0
 */
public class UserAndOtherServiceImpl implements UserService, OtherService {
    @Override
    public void sayHi() {
        System.out.println("say Hi");
    }

    @Override
    public User addUser(User user) {
        System.out.println("addUser invoke");
        return user;
    }

    @Override
    public User updateUser(User user) {
        System.out.println("updateUser invoke");
        return null;
    }

    @Override
    public void delUser(Integer userId) {
        System.out.println("delUser invoke");
    }

    @Override
    public User findUserById(Integer userId) {
        System.out.println("findUserById invoke");
        return null;
    }
}
