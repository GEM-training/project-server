package com.gem.nrserver.service;

import com.gem.nrserver.service.dto.Product;
import com.gem.nrserver.service.dto.Store;
import com.gem.nrserver.service.dto.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by kimtung on 2/24/16.
 */
public interface StoreService extends AbstractService<Store, Long> {

    List<User> listStaffs(Long storeId) throws Exception;
    Page<User> listStaffs(Long storeId, Pageable pageable) throws Exception;
    List<Product> listProducts(Long storeId) throws Exception;
    Page<User> listProducts(Long storeId, Pageable pageable) throws Exception;
    List<User> listCustomers(Long storeId) throws Exception;
    Page<User> listCustomers(Long storeId, Pageable pageable) throws Exception;
    void assignStaff(Long storeId, String userId) throws Exception;
    void assignProduct(Long storeId, Long productId) throws Exception;
}
