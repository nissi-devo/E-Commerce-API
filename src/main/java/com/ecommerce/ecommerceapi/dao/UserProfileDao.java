package com.ecommerce.ecommerceapi.dao;

import com.ecommerce.ecommerceapi.model.entities.UserProfile;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserProfileDao extends CrudRepository<UserProfile, Long> {
     UserProfile findByUserId(String userId);
}
