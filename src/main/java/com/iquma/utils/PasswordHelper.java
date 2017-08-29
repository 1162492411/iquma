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

    /**
     * 加密用户的密码
     * @param user
     */
    public void encryptPassword(User user){
        user.setPass(new SimpleHash(
                algorithmName,
                user.getPass(),
                ByteSource.Util.bytes(user.getSalt()),
                hashIterations).toHex());
    }

    /**
     * 重新设置用户的salt
     * @param user
     */
    public void resetSalt(User user){
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
    }

}
