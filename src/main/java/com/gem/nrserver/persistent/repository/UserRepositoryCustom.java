package com.gem.nrserver.persistent.repository;

import org.springframework.stereotype.Repository;

/**
 * Created by kimtung on 2/26/16.
 */
public interface UserRepositoryCustom {
    boolean isUsernameAvailable(String username);
}
