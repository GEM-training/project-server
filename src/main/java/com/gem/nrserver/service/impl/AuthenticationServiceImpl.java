package com.gem.nrserver.service.impl;

import com.gem.nrserver.persistent.model.*;
import com.gem.nrserver.persistent.repository.PersistentLoginRepository;
import com.gem.nrserver.persistent.repository.UserRepository;
import com.gem.nrserver.service.AuthenticationService;
import com.gem.nrserver.service.dto.UserCredential;
import com.mysema.query.types.expr.BooleanExpression;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class.getName());

    @Autowired
    private PersistentLoginRepository persistentLoginRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserCredential authenticate(String username, String password, String deviceId)
            throws AuthenticationCredentialsNotFoundException {
        BooleanExpression isValidCredential = QUser.user.username.eq(username).and(QUser.user.password.eq(password));
        User user = userRepository.findOne(isValidCredential);
        if(user == null) throw new AuthenticationCredentialsNotFoundException("invalid username or password");
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername(username);
        userCredential.setDeviceId(deviceId);
        ArrayList<String> roles = user.getRoles().stream().map(UserRole::getRole).collect(Collectors.toCollection(ArrayList::new));
        userCredential.setRoles(roles);
        String token = persistentLoginRepository.getToken(username, deviceId);
        if(token != null) {
            userCredential.setToken(token);
        } else {
            PersistentLogin persistentLogin = new PersistentLogin();
            persistentLogin.setUsername(user.getUsername());
            persistentLogin.setDeviceId(deviceId);
            boolean valid = true;
            do {
                try {
                    token = convertStringToMD5(RandomStringUtils.randomAlphanumeric(12));
                    persistentLogin.setToken(token);
                    persistentLoginRepository.save(persistentLogin);
                } catch (Exception e) {
                    valid = false; // token is invalid if exception is thrown
                }
            } while (!valid); // re-generate different token if any exception occurs
            userCredential.setToken(token);
        }
        LOGGER.info("successfully authenticate user " + username);
        return userCredential;
    }

    @Override
    public UserCredential authenticate(String token, String deviceId)
            throws AuthenticationCredentialsNotFoundException {
        BooleanExpression isValidCredential = QPersistentLogin.persistentLogin.token.eq(token)
                .and(QPersistentLogin.persistentLogin.deviceId.eq(deviceId));
        PersistentLogin pLogin = persistentLoginRepository.findOne(isValidCredential);
        if(pLogin == null) throw new AuthenticationCredentialsNotFoundException("invalid credential");
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername(pLogin.getUsername());
        userCredential.setToken(token);
        userCredential.setDeviceId(deviceId);
        ArrayList<String> roles = userRepository.findOne(pLogin.getUsername())
                .getRoles().stream().map(UserRole::getRole).collect(Collectors.toCollection(ArrayList::new));
        userCredential.setRoles(roles);
        LOGGER.info("successfully authenticate user " + userCredential.getUsername());
        return userCredential;
    }

    @Override
    public void deauthenticate(String token) {
        LOGGER.info("deauthenticate token " + token);
        persistentLoginRepository.deleteByToken(token); //delete session token when users logout
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
