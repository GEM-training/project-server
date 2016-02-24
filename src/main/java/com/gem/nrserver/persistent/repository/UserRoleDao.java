package com.gem.nrserver.persistent.repository;


import com.gem.nrserver.persistent.model.UserRole;

import java.util.List;

/**
 * Created by kimtung on 2/17/16.
 */
public interface UserRoleDao extends AbstractDao<UserRole> {

    List<String> findByUsername(String username);

}
