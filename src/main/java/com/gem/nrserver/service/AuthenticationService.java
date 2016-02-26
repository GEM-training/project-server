package com.gem.nrserver.service;


import com.gem.nrserver.persistent.model.User;
import com.gem.nrserver.service.dto.UserDTO;

/**
 * Created by kimtung on 2/18/16.
 */
public interface AuthenticationService {

    String authenticate(String username, String password, String deviceId) throws Exception;

    String getToken(String username, String deviceId);

    boolean isAuthenticated(String token);

    UserDTO getUserFromToken(String token);

    void deauthenticate(String token);

}
