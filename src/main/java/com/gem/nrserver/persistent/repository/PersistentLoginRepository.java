package com.gem.nrserver.persistent.repository;

import com.gem.nrserver.persistent.model.PersistentLogin;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kimtung on 2/17/16.
 */
public interface PersistentLoginRepository extends CrudRepository<PersistentLogin, String>, QueryDslPredicateExecutor<PersistentLogin> {
    @Query("select p.token from PersistentLogin p where p.username = ?1 and p.deviceId = ?2")
    String getToken(String username, String deviceId);

    @Modifying
    @Query("delete from PersistentLogin p where p.token = ?1")
    void deleteByToken(String token);
}
