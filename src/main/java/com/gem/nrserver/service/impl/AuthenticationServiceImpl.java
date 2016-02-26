package com.gem.nrserver.service.impl;

import com.gem.nrserver.persistent.model.PersistentLogin;
import com.gem.nrserver.persistent.model.User;
import com.gem.nrserver.persistent.repository.PersistentLoginRepository;
import com.gem.nrserver.persistent.repository.UserRepository;
import com.gem.nrserver.service.AuthenticationService;
import com.gem.nrserver.service.dto.UserDTO;
import com.gem.nrserver.service.exception.UserNotFoundException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger log = Logger.getLogger(AuthenticationServiceImpl.class.getName());

    @Autowired
    private PersistentLoginRepository persistentLoginRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String authenticate(String username, String password, String deviceId) throws Exception {
        User user = userRepository.findOne(username);
        if(user == null) throw new UserNotFoundException();
        String token = persistentLoginRepository.getToken(username, deviceId);
        if(token != null) {
            return token;
        } else {
            token = convertStringToMD5(RandomStringUtils.randomAlphanumeric(12));
            PersistentLogin persistentLogin = new PersistentLogin();
            persistentLogin.setUsername(user.getUsername());
            persistentLogin.setToken(token);
            persistentLogin.setDeviceId(deviceId);
            persistentLoginRepository.save(persistentLogin);
        }
        return token;
    }

    @Override
    public String getToken(String username, String deviceId) {
        return persistentLoginRepository.getToken(username, deviceId);
    }

    @Override
    public boolean isAuthenticated(String token) {
        return persistentLoginRepository.validate(token);
    }

    @Override
    public UserDTO getUserFromToken(String token) {
        String username = persistentLoginRepository.getUsernameFromToken(token);
        if(username == null) return null;
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(userRepository.findOne(username), dto);
        return dto;
    }

    @Override
    public void deauthenticate(String token) {
        log.info("deauthenticate token " + token);
        persistentLoginRepository.deleteByToken(token);
    }

    private String convertStringToMD5(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());

            byte byteData[] = md.digest();

            // convert the byte to hex format method 1
            StringBuilder sb = new StringBuilder();
            for (byte aByteData : byteData) {
                sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16)
                        .substring(1));
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
