package com.gem.nrserver.persistent.repository;

import com.gem.nrserver.persistent.model.Product;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by kimtung on 2/24/16.
 */
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, QueryDslPredicateExecutor<Product> {
}
