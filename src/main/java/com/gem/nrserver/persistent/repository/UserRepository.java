package com.gem.nrserver.persistent.repository;

import com.gem.nrserver.persistent.model.Product;
import com.gem.nrserver.persistent.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kimtung on 2/26/16.
 */
public interface UserRepository extends
        CrudRepository<User, String>, QueryDslPredicateExecutor<User>, PagingAndSortingRepository<User, String>{

    @Query("select (count(u.username) = 0) from User u where u.username = ?")
    boolean isUsernameAvailable(String username);

    @Query("select p from Product p where p.id in (select distinct(inde.product.id) from InvoiceDetail inde, Invoice inv where inde.invoice.id = inv.id and inv.customer.id = ?)")
    Page<Product> listPurchasedProduct(String userId, Pageable pageable);
}
