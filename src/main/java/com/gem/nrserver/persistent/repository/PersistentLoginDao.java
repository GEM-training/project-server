package com.gem.nrserver.persistent.repository;

import com.gem.nrserver.persistent.model.PersistentLogin;
import com.gem.nrserver.persistent.model.User;

import java.util.List;

/**
 * Created by kimtung on 2/17/16.
 */
public interface PersistentLoginDao extends AbstractDao<PersistentLogin> {
    String getToken(String username, String deviceId);

    List<String> getTokens(User user);

    String getUsernameFromToken(String token);

    boolean validate(String token);

    void deleteByToken(String token);
}
