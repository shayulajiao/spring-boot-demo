package com.hewc.Controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(String userName, String passWord){
        UsernamePasswordToken token = new UsernamePasswordToken(userName,passWord);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return "redirect:/user/list";
        }catch (Exception e){
            e.printStackTrace();
            return "login";
        }
    }
}
