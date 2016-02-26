package com.gem.nrserver.restful;

import com.gem.nrserver.persistent.model.User;
import com.gem.nrserver.restful.dto.ResponseDTO;
import com.gem.nrserver.restful.dto.ResponseUserInfo;
import com.gem.nrserver.restful.dto.UserInfo;
import com.gem.nrserver.service.AuthenticationService;
import com.gem.nrserver.service.UserRoleService;
import com.gem.nrserver.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> register(@RequestBody UserInfo userInfo) {
        User user = new User();
        user.setUsername(userInfo.username);
        user.setPassword(userInfo.password);
        user.setEnabled(true);
        try {
            userService.register(user);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public ResponseDTO login(HttpSession httpSession, @RequestBody UserInfo userInfo) {
        Gson gson = new Gson();
        try {
            String token = authenticationService.authenticate(userInfo.username, userInfo.password, userInfo.deviceId);
            ResponseUserInfo responseUserInfo = new ResponseUserInfo();
            responseUserInfo.setUsername(userInfo.getUsername());
            responseUserInfo.setRole(userRoleService.findByUsername(userInfo.getUsername()));
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
