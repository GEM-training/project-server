package com.gem.nrserver.persistent.repository;

import com.gem.nrserver.persistent.model.User;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kimtung on 2/26/16.
 */
public interface UserRepository extends
        CrudRepository<User, String>, QueryDslPredicateExecutor<User>, UserRepositoryCustom, PagingAndSortingRepository<User, String>{

}