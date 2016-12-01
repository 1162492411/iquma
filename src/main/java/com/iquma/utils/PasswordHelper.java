package com.iquma.utils;

import com.iquma.pojo.User;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

/**
 * Created by Mo on 2016/11/25.
 */
@Component
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private String algorithmName = "md5";
    private final int hashIterations = 2;

    public void encryptPassword(User user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPass(),
                ByteSource.Util.bytes(user.getSalt()),
                hashIterations).toHex();

        user.setPass(newPassword);
    }

    public void encryptPass(User user){
        System.out.println("【加密密码】原密码" + user.getPass() + "\n盐值" + user.getSalt());
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPass(),
                ByteSource.Util.bytes(user.getSalt()),
                hashIterations).toHex();
        System.out.println("【加密密码】加密后的密码为" + newPassword);
        user.setPass(newPassword);
    }


}
