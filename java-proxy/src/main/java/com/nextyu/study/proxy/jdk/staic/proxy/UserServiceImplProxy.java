package com.nextyu.study.proxy.jdk.staic.proxy;


import com.nextyu.study.proxy.domain.User;
import com.nextyu.study.proxy.service.UserService;

/**
 * @author nextyu
 * @version 1.0
 */
public class UserServiceImplProxy implements UserService {

    private UserService userService;

    public UserServiceImplProxy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User addUser(User user) {
        // 做一些额外处理
        System.out.println("begin addUser");
        userService.addUser(new User());
        System.out.println("end addUser");
        return user;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void delUser(Integer userId) {

    }

    @Override
    public User findUserById(Integer userId) {
        return null;
    }
}
