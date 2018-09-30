package com.hewc.Service;

import com.hewc.mapper.UserMapper;
import com.hewc.model.User;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    /**
    * 添加用户
    * */
    public void insert(User user){
        String algorithm = "MD5";
        //将用户名+随机数作为salt
        String salt1 = user.getUsername();
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();

        int hashIterations = 2;//hash迭代次数
        SimpleHash hash = new SimpleHash(algorithm,user.getPassword(),salt1+salt2,hashIterations);
        user.setPassword(hash.toHex());
        user.setSalt(salt2);
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
