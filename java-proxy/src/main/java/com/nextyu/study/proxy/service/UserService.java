package com.nextyu.study.proxy.service;


import com.nextyu.study.proxy.domain.User;

/**
 * @author nextyu
 * @version 1.0
 */
public interface UserService {

    User addUser(User user);

    User updateUser(User user);

    void delUser(Integer userId);

    User findUserById(Integer userId);

}
