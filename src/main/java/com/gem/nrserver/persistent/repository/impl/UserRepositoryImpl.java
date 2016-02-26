package com.gem.nrserver.persistent.repository.impl;

import com.gem.nrserver.persistent.model.*;
import com.gem.nrserver.persistent.repository.UserRepositoryCustom;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import com.mysema.query.jpa.hibernate.HibernateQueryFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kimtung on 2/26/16.
 */
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean isUsernameAvailable(String username) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("username", username));
        return criteria.uniqueResult() == null;
    }
}
