package com.gem.usrmgr.service;

import com.gem.usrmgr.hibernate.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Created by kimtung on 2/17/16.
 */

public interface UserService extends UserDetailsService {

    List<User> list();

    User findByUsername(String username);

    boolean isUsernameAvailable(String username);

    void register(User user) throws IllegalArgumentException;
}
