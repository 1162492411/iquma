package com.iquma.utils;

import com.iquma.pojo.User;
import com.iquma.service.PermissionService;
import com.iquma.service.RolePerService;
import com.iquma.service.RoleService;
import com.iquma.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by Mo on 2016/11/22.
 */
public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RolePerService rolePerService;
    @Autowired
    private PermissionService permissionService;


    public LoginRealm(){
        super();
    }


    //验证登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws ShiroException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.selectById(token.getUsername());//数据库查询用户
        if(null == user) throw new UnknownAccountException();//未找到用户时抛出异常
        else if(user.getIsBlock()) throw new LockedAccountException();//用户被封禁时抛出异常
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        return new SimpleAuthenticationInfo(user.getId(), user.getPass(), ByteSource.Util.bytes(user.getSalt()),getName());
    }


    //登录成功后进行角色和权限验证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();//获取已验证的用户标识
        Byte role = userService.selectById(username).getRid();//获取用户所属的角色
        List permissions =  permissionService.selectByPids(rolePerService.selectPersById(role));//获取用户的角色所拥有的权限集合
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();//将授权信息封装
        authorizationInfo.addRole(String.valueOf(role));//设置用户角色
        authorizationInfo.addStringPermissions(permissions);//设置用户权限
        return authorizationInfo;
    }

    @Override
    public String getName() {
        return super.getName();
    }


    @Override
    protected void clearCache(PrincipalCollection principals) {
        System.out.println("【缓存】清空缓存");
        super.clearCache(principals);
    }
}
