package com.gem.nrserver.persistent.repository;

import java.io.Serializable;

/**
 * Created by kimtung on 2/17/16.
 */
public interface AbstractDao<T> {

    T loadById(Serializable id);

    T getById(Serializable id);

    void persit(Object o);

    void update(Object o);

    void delete(Object o);

    void deleteById(Serializable id);

    void refresh(Object o);

}
