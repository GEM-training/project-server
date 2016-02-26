package com.gem.nrserver.service.impl;

import com.gem.nrserver.persistent.model.PersistentLogin;
import com.gem.nrserver.persistent.model.QPersistentLogin;
import com.gem.nrserver.persistent.model.QUser;
import com.gem.nrserver.persistent.model.User;
import com.gem.nrserver.persistent.repository.PersistentLoginRepository;
import com.gem.nrserver.persistent.repository.UserRepository;
import com.gem.nrserver.service.AuthenticationService;
import com.gem.nrserver.service.dto.UserCredential;
import com.mysema.query.types.expr.BooleanExpression;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
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
    public UserCredential authenticate(String username, String password, String deviceId)
            throws AuthenticationCredentialsNotFoundException {
        BooleanExpression isValidCredential = QUser.user.username.eq(username).and(QUser.user.password.eq(password));
        User user = userRepository.findOne(isValidCredential);
        if(user == null) throw new AuthenticationCredentialsNotFoundException("invalid username or password");
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername(username);
        userCredential.setDeviceId(deviceId);
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
        return userCredential;
    }

    @Override
    public void deauthenticate(String token) {
        log.info("deauthenticate token " + token);
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
