package com.gem.nrserver.persistent.repository;

import com.gem.nrserver.persistent.model.PersistentLogin;
import com.gem.nrserver.persistent.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by kimtung on 2/17/16.
 */
public interface PersistentLoginRepository extends CrudRepository<PersistentLogin, String> {
    @Query("select p.token from PersistentLogin p where p.username = ? and p.deviceId = ?")
    String getToken(String username, String deviceId);

    @Query("select p.token from PersistentLogin p where p.username = ?")
    List<String> getTokens(String username);

    @Query("select p.username from PersistentLogin p where p.token = ?")
    String getUsernameFromToken(String token);

    @Query("select (count(p.token) <> 0) from PersistentLogin p where p.token = ?")
    boolean validate(String token);

    @Query("delete from PersistentLogin p where p.token = ?")
    void deleteByToken(String token);
}
