package com.ecommerce.ecommerceapi.model.entities;

import com.ecommerce.ecommerceapi.model.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")

public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Double price;
    private Double netPrice;
    private String deliveryMethod;
    private String paymentMethod;
    private Double shippingCost;
    private Double discount;
    private String description;
    private String userId;
    private Long productId;
    @CreationTimestamp
    private Date dateCreated;
    @UpdateTimestamp
    private Date dateModified;
}


