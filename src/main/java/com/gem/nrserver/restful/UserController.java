package com.gem.nrserver.restful;

import com.gem.nrserver.persistent.model.User;
import com.gem.nrserver.restful.dto.ResponseUserInfo;
import com.gem.nrserver.service.AuthenticationService;
import com.gem.nrserver.service.UserRoleService;
import com.gem.nrserver.service.UserService;
import com.gem.nrserver.service.dto.ProductDTO;
import com.gem.nrserver.service.dto.UserDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public List<ProductDTO> listPurchasedProduct(@PathVariable(value = "id") String userId) throws Exception {
        return userService.listPurchasedProduct(userId);
    }

}
