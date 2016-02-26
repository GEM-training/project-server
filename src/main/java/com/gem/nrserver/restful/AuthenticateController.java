package com.gem.nrserver.restful;

import com.gem.nrserver.service.AuthenticationService;
import com.gem.nrserver.service.UserService;
import com.gem.nrserver.service.dto.UserCredential;
import com.gem.nrserver.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/authenticate")
public class AuthenticateController {

    @Autowired
    @Qualifier("service_user")
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    public void register(@RequestBody UserDTO userDTO) throws Exception {
        userService.create(userDTO);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public UserCredential login(@RequestBody UserCredential userCredential) throws Exception {
        return authenticationService.authenticate(userCredential.getUsername(), userCredential.getPassword(), userCredential.getDeviceId());
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET,produces = "application/json")
    public void logout(HttpServletRequest request) {
        authenticationService.deauthenticate(request.getHeader("token"));
    }

}
