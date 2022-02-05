package com.ecommerce.ecommerceapi.api;

import com.ecommerce.ecommerceapi.model.JwtRequest;
import com.ecommerce.ecommerceapi.model.JwtResponse;
import com.ecommerce.ecommerceapi.model.Response;
import com.ecommerce.ecommerceapi.model.entities.User;
import com.ecommerce.ecommerceapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value="/signup")
    public  Response signup(@RequestBody User user){
        return userService.signup(user);
    }

    @PostMapping(value="/login")
    public JwtResponse login(@RequestBody JwtRequest authenticationRequest) throws Exception {
        return userService.login(authenticationRequest);
    }

}


