package com.ljz.dao;

import com.ljz.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {

   int insertUser(User user);

   int deleteUser(Integer uid);

   int updateUser(User user);

   User selectUser(@Param("uname") String uname,@Param("upwd") String upwd);

}
