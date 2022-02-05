package com.ecommerce.ecommerceapi.api;

import com.ecommerce.ecommerceapi.dao.ProductDao;
import com.ecommerce.ecommerceapi.model.Response;
import com.ecommerce.ecommerceapi.model.entities.Product;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/product")
@RestController
public class ProductController {

    @Autowired
    ProductDao productDao;
    @GetMapping
    public Response<Product> getProducts(
            @RequestParam(defaultValue = "") String productionCountry
    ){
        return new Response(productDao.findByProductionCountry(productionCountry));
    }
}
