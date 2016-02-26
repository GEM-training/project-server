package com.gem.nrserver.service;

import com.gem.nrserver.service.dto.ProductDTO;
import com.gem.nrserver.service.dto.StoreDTO;
import com.gem.nrserver.service.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by kimtung on 2/24/16.
 */
public interface StoreService extends AbstractService<StoreDTO, Long> {

    List<UserDTO> listStaffs(Long storeId) throws Exception;
    Page<UserDTO> listStaffs(Long storeId, Pageable pageable) throws Exception;
    List<ProductDTO> listProducts(Long storeId) throws Exception;
    Page<ProductDTO> listProducts(Long storeId, Pageable pageable) throws Exception;
    List<UserDTO> listCustomers(Long storeId) throws Exception;
    Page<UserDTO> listCustomers(Long storeId, Pageable pageable) throws Exception;
    void assignStaff(Long storeId, String userId) throws Exception;
    void assignProduct(Long storeId, Long productId) throws Exception;
}
