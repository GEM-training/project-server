package com.gem.nrserver.persistent.repository;

import com.gem.nrserver.persistent.model.Product;
import com.gem.nrserver.persistent.model.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by kimtung on 2/24/16.
 */
public interface StoreRepository extends PagingAndSortingRepository<Store, Long>, QueryDslPredicateExecutor<Store> {

    @Query("select p from Product p where p.id in (select ps.product.id from ProductStore ps where ps.store.id = ?)")
    Page<Product> listProducts(Long storeId, Pageable pageable);

}
