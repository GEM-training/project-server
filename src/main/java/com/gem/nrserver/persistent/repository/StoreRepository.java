package com.gem.nrserver.persistent.repository;

import com.gem.nrserver.persistent.model.Store;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by kimtung on 2/24/16.
 */
public interface StoreRepository extends PagingAndSortingRepository<Store, Long>, QueryDslPredicateExecutor<Store> {
}
