package com.gem.usrmgr.rest.controller;

import com.gem.usrmgr.hibernate.model.User;
import com.gem.usrmgr.rest.dto.ResponseUserInfo;
import com.gem.usrmgr.service.AuthenticationService;
import com.gem.usrmgr.service.UserRoleService;
import com.gem.usrmgr.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    private AuthenticationService authenticationService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    @Qualifier("service_user")
    private UserService userService;

    @RequestMapping(value = "/ls", method = RequestMethod.GET)
    public List<User> list() {
        return userService.list();
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseEntity<String> getUserInfo(HttpServletRequest request) {
        Gson gson = new Gson();

        String token = request.getHeader("token");
        User user = authenticationService.getUserFromToken(token);

        ResponseUserInfo responseUserInfo = new ResponseUserInfo();
        responseUserInfo.setUsername(user.getUsername());
        responseUserInfo.setRole(userRoleService.findByUsername(user.getUsername()));
        responseUserInfo.setToken(token);

        return new ResponseEntity<String>(gson.toJson(responseUserInfo), HttpStatus.OK);
    }

}
