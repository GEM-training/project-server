package com.gem.nrserver.service;

import com.gem.nrserver.service.dto.InvoiceDTO;
import com.gem.nrserver.service.dto.ProductDTO;
import com.gem.nrserver.service.dto.StoreDTO;
import com.gem.nrserver.service.dto.UserDTO;
import com.gem.nrserver.service.exception.StoreNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface StoreService extends AbstractService<StoreDTO, Long> {
    Page<UserDTO> listStaffs(Long storeId, Pageable pageable) throws StoreNotFoundException;
    Page<ProductDTO> listProducts(Long storeId, Pageable pageable) throws StoreNotFoundException;
    Page<ProductDTO> listProducts(Long storeId, Date from, Date to, Pageable pageable) throws StoreNotFoundException;
    Page<UserDTO> listCustomers(Long storeId, Pageable pageable) throws StoreNotFoundException;
    Page<UserDTO> listCustomers(Long storeId, Date from, Date to, Pageable pageable) throws StoreNotFoundException;
    Page<InvoiceDTO> listInvoices(Long storeId, Pageable pageable) throws StoreNotFoundException;
    Page<InvoiceDTO> listInvoices(Long storeId, Date from, Date to, Pageable pageable) throws StoreNotFoundException;
    void assignStaff(Long storeId, String userId);
    void assignProduct(Long storeId, Long productId);
}
