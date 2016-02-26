package com.gem.nrserver.service;

import com.gem.nrserver.service.dto.ProductDTO;
import com.gem.nrserver.service.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by kimtung on 2/17/16.
 */

public interface UserService extends AbstractService<UserDTO, String> {
    boolean isUsernameAvailable(String username);
    Page<ProductDTO> listPurchasedProduct(String userId, Pageable pageable) throws Exception;
}
