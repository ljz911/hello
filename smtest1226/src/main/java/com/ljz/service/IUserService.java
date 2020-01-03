package com.ljz.service;

import com.ljz.pojo.User;

public interface IUserService {

    int insertUser(User user);

    int deleteUser(Integer uid);

    int updateUser(User user);

    User selectUser(User user);
}
