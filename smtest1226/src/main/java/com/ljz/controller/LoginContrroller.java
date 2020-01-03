package com.ljz.controller;

import com.ljz.dao.UserDao;
import com.ljz.pojo.User;
import com.ljz.service.UserService;
import com.ljz.utils.Verification;
import com.ljz.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title LoginContrroller
 * @Description ${}
 * @Author 刘敬祯
 * @CreateTime 2019/12/26 9:35
 * @Version 1.0
 **/
@RestController
public class LoginContrroller {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public Map<String,Object> login(String username,String password,String varify, HttpSession session){
        User user1 = new User();
        user1.setUname(username);
        user1.setUpwd(password);
        System.out.println(user1);
        Map<String,Object> map = new HashMap<>();
        String signcode = (String) session.getAttribute("signcode");
        if(varify.equalsIgnoreCase(signcode)  ){
            User user = null;
            try {
                user = userDao.selectUser(user1.getUname(),user1.getUpwd());
            }catch (NullPointerException e){
            }
            if (user!=null){
                session.setAttribute("username", user.getUname());
                map.put("user", user);
                map.put("msg", "登陆成功");
                return map;
            }else{

                map.put("user", null);
                map.put("msg", "登陆失败，用户名密码错误");
                return map;
            }

       }else {
            map.put("msg", "验证码错误");
            map.put("user",null );
            return map;
        }

    }

    @RequestMapping("/register")
    public String register(User user){
        System.out.println(user);
        int now = Calendar.getInstance().get(Calendar.YEAR);
        Calendar birth = Calendar.getInstance();
        birth.setTime(user.getBirth());
        int age = now - birth.get(Calendar.YEAR);
        user.setUage(age);
        Integer i = userDao.insertUser(user);
        if(i==0){
            return "failed";
        }else {
            return "hello";
        }
    }

    @RequestMapping("/queryInfo")
    public Map<String,Object> queryInfo(HttpSession session){
        Map<String,Object> map = new HashMap<>();
        map.put("username", session.getAttribute("username"));
        return map;
    }

    @RequestMapping("/varify")
    public void Verify(HttpSession session, HttpServletResponse response) throws IOException {

        //1.生成随机的验证码及图片
        Object[] objs = VerifyUtil.createImage();
        //2.将验证码存入session
        String imgcode = (String) objs[0];
        session.setAttribute("imgcode", imgcode);
        System.out.println(imgcode);
        //3.将图片输出给浏览器
        BufferedImage img = (BufferedImage) objs[1];
        response.setContentType("image/png");
        //服务器自动创建输出流，目标指向浏览器
        OutputStream os ;

        try {
            os = response.getOutputStream();
            ImageIO.write(img, "jpg", os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 
      * @Description: 生成验证码图片
      * @Param: 
      * @return: 
      * @Author: 刘敬祯
      * @Date: 2019/12/30 12:52
      */
    @RequestMapping("/verification")
    public void verification(HttpSession session,HttpServletRequest request, HttpServletResponse response){

        Verification verificationCode = new Verification();
        //获取验证码图片
        BufferedImage image = verificationCode.getImage();
        //获取验证码内容
        String text = verificationCode.getText();
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        randomCode.append(text);
        // 将验证码保存到Session中。
        session.setAttribute("signcode", randomCode.toString());
        System.out.println("session-signcode==>"+randomCode.toString());
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = null;
        try {
            sos = response.getOutputStream();
            ImageIO.write(image, "jpeg", sos);
            sos.flush();
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
