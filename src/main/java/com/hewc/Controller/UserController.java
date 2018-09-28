package com.hewc.Controller;

import com.hewc.Service.UserService;
import com.hewc.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequiresPermissions("user:list")
    @RequestMapping("/list")
    public ModelAndView findAllUsers(){
        ModelAndView modelAndView = new ModelAndView("userList");
        List<User> userList = userService.findAllUsers();
        modelAndView.addObject(userList);
        return modelAndView;
    }

    @RequiresPermissions(value={"user:update","user:add"},logical = Logical.OR)
    @RequestMapping("/userInfo")
    public ModelAndView userInfo(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("userInfo");
        String id = request.getParameter("id");
        if(!StringUtils.isEmpty(id)){
            User user = userService.selectByPrimaryKey(Integer.valueOf(id));
            modelAndView.addObject("user",user);
        }
        return modelAndView;
    }

    @RequiresPermissions("user:delete")
    @RequestMapping("/delete/{id}")
    public Object delete(@PathVariable String id){
        userService.deleteByPrimaryKey(Integer.valueOf(id));

        Map<String,Object> returnVal = new HashMap<>();
        returnVal.put("success",true);
        return returnVal;
    }

    @RequestMapping("/saveInfo")
    public Object saveInfo(User user){
        if(user.getId() == null){
            userService.insert(user);
        }else {
            userService.updateByPrimaryKeySelective(user);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }
}
