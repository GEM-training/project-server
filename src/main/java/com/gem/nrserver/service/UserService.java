package com.gem.nrserver.service;

import com.gem.nrserver.service.dto.InvoiceDTO;
import com.gem.nrserver.service.dto.ProductDTO;
import com.gem.nrserver.service.dto.UserDTO;
import com.gem.nrserver.service.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService extends AbstractService<UserDTO, String> {
    boolean isUsernameAvailable(String username);
    Page<ProductDTO> listPurchasedProducts(String userId, Pageable pageable) throws UserNotFoundException;
    Page<InvoiceDTO> listInvoices(String userId, Pageable pageable) throws UserNotFoundException;
}
