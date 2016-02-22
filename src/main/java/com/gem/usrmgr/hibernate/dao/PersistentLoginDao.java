package com.gem.usrmgr.hibernate.dao;

import com.gem.usrmgr.hibernate.model.PersistentLogin;
import com.gem.usrmgr.hibernate.model.User;

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
