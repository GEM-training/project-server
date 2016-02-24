package com.gem.nrserver.persistent.repository.impl;

import com.gem.nrserver.persistent.model.User;
import com.gem.nrserver.persistent.repository.UserDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kimtung on 2/17/16.
 */
@Repository()
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public List<User> list() {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.addOrder(Order.asc("username"));
        return criteria.list();
    }

    @Override
    public User findByUsername(String username) {
        return getById(username);
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("username", username));
        return criteria.uniqueResult() == null;
    }
}
