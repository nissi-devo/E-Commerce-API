package com.ecommerce.ecommerceapi.dao;

import com.ecommerce.ecommerceapi.model.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserDao extends CrudRepository<User, Long> {
    Optional  <User> findById(Long Id);
    User findByUsername (String username);
}
