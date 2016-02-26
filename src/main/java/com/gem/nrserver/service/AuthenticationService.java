package com.gem.nrserver.service;


import com.gem.nrserver.service.dto.UserCredential;


public interface AuthenticationService {

    UserCredential authenticate(String username, String password, String deviceId) throws Exception;
    UserCredential authenticate(String token, String deviceId) throws Exception;
    void deauthenticate(String token);

}
