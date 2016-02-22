package com.gem.usrmgr.hibernate.dao.impl;

import com.gem.usrmgr.hibernate.dao.UserRoleDao;
import com.gem.usrmgr.hibernate.model.UserRole;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by quanda on 19/02/2016.
 */
@Repository
public class UserRoleDaoImpl extends AbstractDaoImpl<UserRole> implements UserRoleDao {

    public UserRoleDaoImpl() {
        super(UserRole.class);
    }

    @Override
    public List<String> findByUsername(String username) {
        SQLQuery query = getSession().createSQLQuery("select role from user_roles where username = :username");
        query.setString("username", username);
        return query.list();
    }
}
