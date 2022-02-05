package com.ecommerce.ecommerceapi.services;

import com.ecommerce.ecommerceapi.dao.UserDao;
import com.ecommerce.ecommerceapi.model.JwtRequest;
import com.ecommerce.ecommerceapi.model.JwtResponse;
import com.ecommerce.ecommerceapi.model.Response;
import com.ecommerce.ecommerceapi.model.entities.User;
import com.ecommerce.ecommerceapi.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userDao.findByUsername(username);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private JwtResponse getToken(String username, String password) throws Exception {

        authenticate(username, password);

        UserDetails userDetails = loadUserByUsername(username);

        String token = jwtTokenUtil.generateToken(userDetails);

        return new JwtResponse(token);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    public JwtResponse login(JwtRequest authenticationRequest) throws Exception {

        var token = getToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        return token;
    }


    public Response  <User> signup(User user){
       var  userProfile = User.builder().
               email(user.getEmail())
               .username(user.getEmail())
               .password(passwordEncoder.encode(user.getPassword()))
               .build();

       userDao.save(userProfile);

       return new Response<>(userProfile);


    }
}
