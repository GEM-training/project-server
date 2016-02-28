package com.gem.nrserver.persistent.repository;

import com.gem.nrserver.persistent.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, QueryDslPredicateExecutor<Product> {

    @Query("select p from Product p where p.id in (select ps.product.id from ProductStore ps where ps.store.id = ?1)")
    Page<Product> listProductsInStore(Long storeId, Pageable pageable);

    @Query("select p from Product p where p.id in (select distinct(inde.product.id) from InvoiceDetail inde, Invoice inv where inde.invoice.id = inv.id and inv.customer.id = ?1)")
    Page<Product> listProductPurchasedByUser(String userId, Pageable pageable);
}
