package com.gem.nrserver.persistent.repository;

import com.gem.nrserver.persistent.model.Store;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends PagingAndSortingRepository<Store, Long>, QueryDslPredicateExecutor<Store> {

}
