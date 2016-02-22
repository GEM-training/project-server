package com.gem.usrmgr.service;

import com.gem.usrmgr.hibernate.model.User;

/**
 * Created by kimtung on 2/18/16.
 */
public interface AuthenticationService {

    String authenticate(String username, String password, String deviceId);

    String getToken(String username, String deviceId);

    boolean isAuthenticated(String token);

    User getUserFromToken(String token);

    void deauthenticate(String token);

}
