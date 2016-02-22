package com.gem.usrmgr.hibernate.dao;

import com.gem.usrmgr.hibernate.model.UserRole;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kimtung on 2/17/16.
 */
public interface UserRoleDao extends AbstractDao<UserRole> {

    List<String> findByUsername(String username);

}
