package com.gem.nrserver.service;


import com.gem.nrserver.service.dto.UserCredential;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;


public interface AuthenticationService {

    UserCredential authenticate(String username, String password, String deviceId) throws AuthenticationCredentialsNotFoundException;
    UserCredential authenticate(String token, String deviceId) throws AuthenticationCredentialsNotFoundException;
    void deauthenticate(String token);

}
