package com.gem.nrserver.restful;

import com.gem.nrserver.service.UserService;
import com.gem.nrserver.service.dto.ProductDTO;
import com.gem.nrserver.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kimtung on 2/17/16.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("service_user")
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public Page<UserDTO> list(Pageable pageable) {
        return userService.findAll(pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public UserDTO getById(@PathVariable(value = "id") String userId) throws Exception {
        return userService.findOne(userId);
    }

    @RequestMapping(value = "/{id}/product", method = RequestMethod.GET, produces = "application/json")
    public Page<ProductDTO> listPurchasedProduct(@PathVariable(value = "id") String userId, Pageable pageable) throws Exception {
        return userService.listPurchasedProducts(userId, pageable);
    }

}
