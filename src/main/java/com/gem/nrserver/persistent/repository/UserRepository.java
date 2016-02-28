package com.gem.nrserver.persistent.repository;

import com.gem.nrserver.persistent.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends QueryDslPredicateExecutor<User>, PagingAndSortingRepository<User, String>{

    @Query("select (count(u.username) = 0) from User u where u.username = ?1")
    boolean isUsernameAvailable(String username);
}
