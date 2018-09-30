package com.hewc.ShiroConfig;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
* 密码错误重试次数不能超过五次
* */
public class RetryLimitCredentialMatcher extends HashedCredentialsMatcher{

    //缓存登录失败的次数
    public static Map<String,AtomicInteger> loginTimes = new ConcurrentHashMap<>();


    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String userName = (String) token.getPrincipal();

        boolean matches = super.doCredentialsMatch(token, info);
        if(matches){
            loginTimes.remove(userName);
        }else if(loginTimes.containsKey(userName)){
            //重试次数大于五次抛出异常
           AtomicInteger retryCount = loginTimes.get(userName);
           if(retryCount.getAndIncrement() > 5){
               throw new ExcessiveAttemptsException();
           }
        }else {
            AtomicInteger retryCount = new AtomicInteger(1);
            loginTimes.put(userName,retryCount);
        }

        return matches;
    }
}
