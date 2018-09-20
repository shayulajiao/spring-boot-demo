package com.hewc.ShiroConfig;

import com.hewc.Service.PermissionService;
import com.hewc.Service.RoleService;
import com.hewc.Service.UserService;
import com.hewc.model.Role;
import com.hewc.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MyShiroReaml extends AuthorizingRealm{

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    /**
    * 角色权限和对应权限添加
    * */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户对应的角色
        String userName = (String) principalCollection.getPrimaryPrincipal();
        List<Role> roles = roleService.findAllRolesByName(userName);

        //添加角色及权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<String> permissions = new ArrayList<>();
        for(Role role:roles){
            simpleAuthorizationInfo.addRole(role.getName());
            permissions.addAll(permissionService.findResourceByRole(role.getId()));
        }
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    /**
    * 用户登录认证
    * */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();

        User user = userService.findByName(userName);
        //账号不存在
        if(user == null){
            throw new UnknownAccountException();
        }
        //账号被锁定
        if(user.getLocked()){
            throw new LockedAccountException();
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
                                                                                         user.getPassword(),
                                                                                         ByteSource.Util.bytes(user.getCredentialsSalt()),
                                                                                         getName());
        return simpleAuthenticationInfo;
    }
}
