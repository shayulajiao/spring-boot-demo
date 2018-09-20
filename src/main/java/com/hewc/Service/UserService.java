package com.hewc.Service;

import com.hewc.mapper.UserMapper;
import com.hewc.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public void insert(User user){
        userMapper.insert(user);
    }

    public void updateByPrimaryKeySelective(User user){
        userMapper.updateByPrimaryKeySelective(user);
    }

    public User selectByPrimaryKey(int id){
        return userMapper.selectByPrimaryKey(id);
    }

    public void deleteByPrimaryKey(int id){
        userMapper.deleteByPrimaryKey(id);
    }

    public List<User> findAllUsers(){
        return userMapper.findAllUsers();
    }

    public User findByName(String userName){
        return userMapper.findByName(userName);
    }
}
