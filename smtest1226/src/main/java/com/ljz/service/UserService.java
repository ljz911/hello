package com.ljz.service;

import com.ljz.dao.UserDao;
import com.ljz.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @Title UserService
 * @Description ${}
 * @Author 刘敬祯
 * @CreateTime 2019/12/30 12:43
 * @Version 1.0
 **/

@Service
public class UserService implements IUserService {

    private UserDao  userDao;

    @Override
    public int insertUser(User user) {
        int res = 0;
        try{
            res = userDao.insertUser(user);
        }catch (Exception e){

        }
        return res;
    }

    @Override
    public int deleteUser(Integer uid) {
        return userDao.deleteUser(uid);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public User selectUser(User user) {
        User user1 = null;
        try{
            user1 =  userDao.selectUser(user.getUname(), user.getUpwd());
        }catch (NullPointerException e){
        }
        return user1;
    }
}
