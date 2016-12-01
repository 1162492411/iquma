package com.iquma.realm;

import com.iquma.pojo.User;
import com.iquma.service.PermissionService;
import com.iquma.service.RolePerService;
import com.iquma.service.RoleService;
import com.iquma.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
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
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.selectById(token.getUsername());//数据库查询用户
        System.out.println("【验证登陆】从数据库查询到了用户" + user);
        if(Boolean.TRUE.equals(user.getIsBlock())){//若用户被封禁
            throw new LockedAccountException();
        }
        else if(user == null){//若未找到该用户
            throw new UnknownAccountException("未找到该账号");
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        return new SimpleAuthenticationInfo(user.getId(), user.getPass(), ByteSource.Util.bytes(user.getSalt()),getName());
    }


    //登录成功后进行角色和权限验证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();//获取已验证的用户标识
        System.out.println("【角色和权限认证】获取到已验证的用户标识" + username);

        Byte role = userService.selectById(username).getRid();//获取用户所属的角色
        List permissions =  permissionService.selectByPids(rolePerService.selectPersById(role));//获取用户的角色所拥有的权限集合


        System.out.println("该账户拥有的角色是" + role);
        System.out.println("该角色拥有的权限包括:");
        for (Object per : permissions) {
            System.out.println(per + ", ");
        }



        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();//将授权信息封装
        authorizationInfo.addRole(String.valueOf(role));
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
