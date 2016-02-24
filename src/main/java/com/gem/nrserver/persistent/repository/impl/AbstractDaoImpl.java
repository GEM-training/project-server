package com.gem.nrserver.persistent.repository.impl;

import com.gem.nrserver.persistent.repository.AbstractDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * Created by kimtung on 2/17/16.
 */
public class AbstractDaoImpl<T> implements AbstractDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class dataClass;

    public AbstractDaoImpl(Class dataClass) {
        this.dataClass = dataClass;
    }

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public T loadById(Serializable id) {
        return (T) getSession().load(dataClass, id);
    }

    @Override
    public T getById(Serializable id) {
        return (T) getSession().get(dataClass, id);
    }

    @Override
    public void persit(Object object) {
        getSession().persist(object);
    }

    @Override
    public void update(Object object) {
        getSession().update(object);
    }

    @Override
    public void delete(Object object) {
        getSession().delete(object);
    }

    @Override
    public void deleteById(Serializable id) {
        Object o = this.loadById(id);
        getSession().delete(o);
    }

    @Override
    public void refresh(Object o) {
        getSession().refresh(o);
    }
}
