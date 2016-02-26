package com.gem.nrserver.restful;

import com.gem.nrserver.restful.dto.ResponseDTO;
import com.gem.nrserver.restful.dto.ResponseUserInfo;
import com.gem.nrserver.service.dto.UserAuthenticationInfo;
import com.gem.nrserver.service.AuthenticationService;
import com.gem.nrserver.service.UserRoleService;
import com.gem.nrserver.service.UserService;
import com.gem.nrserver.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by kimtung on 2/17/16.
 */
@RestController
@RequestMapping()
public class LoginController {

    @Autowired
    @Qualifier("service_user")
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    public void register(@RequestBody UserDTO userDTO) throws Exception {
        userService.create(userDTO);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public ResponseDTO login(HttpSession httpSession, @RequestBody UserAuthenticationInfo userAuthenticationInfo) throws Exception {
        try {
            String token = authenticationService.authenticate(userAuthenticationInfo.username, userAuthenticationInfo.password, userAuthenticationInfo.deviceId);
            ResponseUserInfo responseUserInfo = new ResponseUserInfo();
            responseUserInfo.setUsername(userAuthenticationInfo.getUsername());
            responseUserInfo.setRole(userRoleService.findByUsername(userAuthenticationInfo.getUsername()));
            responseUserInfo.setToken(token);

            return new ResponseDTO(HttpStatus.OK.value(),null,responseUserInfo);
        } catch (IllegalArgumentException e) {
            return new ResponseDTO(HttpStatus.OK.value(),e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/logoutuser", method = RequestMethod.GET,produces = "application/json")
    public ResponseDTO logout(HttpServletRequest request) {
        authenticationService.deauthenticate(request.getHeader("token"));
        return new ResponseDTO(HttpStatus.OK.value(),"Logout successful",null);
    }

}
