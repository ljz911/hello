package com.ljz;

import com.ljz.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class Smtest1226ApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDao userDao;

    @Test
    void contextLoads()  {
        System.out.println(dataSource.getClass());
        try {
            System.out.println(dataSource.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

     /*   User user = new User(null, "test1", "123", 21, new Date(), 1);
        Integer i= null;


        try {
            i = userDao.insertUser(user);
        }catch (Exception e){

        }
        System.out.println(i==null?0:1);

        User user2 = userDao.selectUser("test", "123");
        System.out.println(user2);*/

}

}
