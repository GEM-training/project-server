package com.gem.usrmgr.service;

import com.gem.usrmgr.hibernate.model.UserRole;

import java.util.List;

/**
 * Created by quanda on 19/02/2016.
 */
public interface UserRoleService {

    List<String> findByUsername(String username);

}
