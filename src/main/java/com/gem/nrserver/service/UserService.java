package com.gem.nrserver.service;

import com.gem.nrserver.persistent.model.User;
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
