package com.gem.nrserver.persistent.repository.impl;

import com.gem.nrserver.persistent.model.PersistentLogin;
import com.gem.nrserver.persistent.model.User;
import com.gem.nrserver.persistent.repository.PersistentLoginDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kimtung on 2/17/16.
 */
@Repository
public class PersistentLoginDaoImpl extends AbstractDaoImpl<PersistentLogin> implements PersistentLoginDao {

    public PersistentLoginDaoImpl() {
        super(PersistentLogin.class);
    }

    @Override
    public String getToken(String username, String deviceId) {
        Criteria criteria = getSession().createCriteria(PersistentLogin.class);
        criteria.add(Restrictions.eq("username", username))
                .add(Restrictions.eq("deviceId", deviceId));
        PersistentLogin pl = (PersistentLogin) criteria.uniqueResult();
        return pl == null ? null : pl.getToken();
    }

    @Override
    public List<String> getTokens(User user) {
        Criteria criteria = getSession().createCriteria(PersistentLogin.class);
        criteria.add(Restrictions.eq("username", user.getUsername()));
        List<PersistentLogin> persistentLogins = criteria.list();
        ArrayList<String> tokens = new ArrayList<>(persistentLogins.size());
        tokens.addAll(persistentLogins.stream().map(PersistentLogin::getToken).collect(Collectors.toList()));
        return tokens;
    }

    @Override
    public String getUsernameFromToken(String token) {
        Criteria criteria = getSession().createCriteria(PersistentLogin.class);
        criteria.add(Restrictions.eq("token", token));
        return criteria.uniqueResult() == null ? null : ((PersistentLogin) criteria.uniqueResult()).getUsername();
    }

    @Override
    public boolean validate(String token) {
        Criteria criteria = getSession().createCriteria(PersistentLogin.class);
        criteria.add(Restrictions.eq("token", token));
        return criteria.uniqueResult() != null;
    }

    @Override
    public void deleteByToken(String token) {
        Criteria criteria = getSession().createCriteria(PersistentLogin.class);
        criteria.add(Restrictions.eq("token", token));
        PersistentLogin persistentLogin = (PersistentLogin) criteria.uniqueResult();
        if (persistentLogin != null) {
            delete(persistentLogin);
        }
    }
}
