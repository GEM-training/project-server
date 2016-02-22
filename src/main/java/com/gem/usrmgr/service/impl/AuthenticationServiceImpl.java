package com.gem.usrmgr.service.impl;

import com.gem.usrmgr.hibernate.dao.PersistentLoginDao;
import com.gem.usrmgr.hibernate.model.PersistentLogin;
import com.gem.usrmgr.hibernate.model.User;
import com.gem.usrmgr.service.AuthenticationService;
import com.gem.usrmgr.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;

/**
 * Created by kimtung on 2/18/16.
 */
@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private static Logger log = Logger.getLogger(AuthenticationServiceImpl.class.getName());

    @Autowired
    private UserService userService;

    @Autowired
    private PersistentLoginDao persistentLoginDao;

    @Override
    public String authenticate(String username, String password, String deviceId) throws IllegalArgumentException {
        User user = userService.findByUsername(username);
        if(user == null || !user.getPassword().equals(password)) {
            log.info("authentication failed: user " + username + " with device " + deviceId);
            throw new IllegalArgumentException("invalid username and password");
        }
        String token = persistentLoginDao.getToken(username, deviceId);
        if(token != null) {
            return token;
        } else {
            token = convertStringToMD5(RandomStringUtils.randomAlphanumeric(12));
            PersistentLogin persistentLogin = new PersistentLogin();
            persistentLogin.setUsername(user.getUsername());
            persistentLogin.setToken(token);
            persistentLogin.setDeviceId(deviceId);
            persistentLoginDao.persit(persistentLogin);
        }
        log.info("authentication succeeded for user " + username + " with device " + deviceId);
        return token;
    }

    @Override
    public String getToken(String username, String deviceId) {
        return persistentLoginDao.getToken(username, deviceId);
    }

    @Override
    public boolean isAuthenticated(String token) {
        return persistentLoginDao.validate(token);
    }

    @Override
    public User getUserFromToken(String token) {
        String username = persistentLoginDao.getUsernameFromToken(token);
        return userService.findByUsername(username);
    }

    @Override
    public void deauthenticate(String token) {
        log.info("deauthenticate token " + token);
        persistentLoginDao.deleteByToken(token);
    }

    private String convertStringToMD5(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());

            byte byteData[] = md.digest();

            // convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
