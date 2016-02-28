package com.gem.nrserver.persistent.repository;


import com.gem.nrserver.persistent.model.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, String> {
    @Query("select r.role from UserRole r where r.user.username = ?1")
    List<String> findByUsername(String username);
}
