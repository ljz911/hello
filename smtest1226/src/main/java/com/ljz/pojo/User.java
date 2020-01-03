package com.ljz.pojo;

import com.ljz.utils.Md5;

import java.util.Date;

/**
 * @Title user
 * @Description ${}
 * @Author 刘敬祯
 * @CreateTime 2019/12/26 9:32
 * @Version 1.0
 **/


public class User {
    private Integer uid;
    private String uname;
    private String upwd;
    private Integer uage;
    private Date birth;
    private Integer usex;


    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", upwd='" + upwd + '\'' +
                ", uage=" + uage +
                ", birth=" + birth +
                ", usex=" + usex +
                '}';
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = Md5.getMD5(upwd, 10);
    }

    public Integer getUage() {
        return uage;
    }

    public void setUage(Integer uage) {
        this.uage = uage;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getUsex() {
        return usex;
    }

    public void setUsex(Integer usex) {
        this.usex = usex;
    }
}
