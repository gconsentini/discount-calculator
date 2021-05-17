package com.gconsentini.productsapi.controllers;

import com.gconsentini.productsapi.models.Product;
import com.gconsentini.productsapi.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProcuctsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/products")
    public List<Product> getAllProducts(@RequestHeader("user-id") String userId){
        return productsService.getAllProducts(userId);
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable String id){
        return productsService.getProductById(id);
    }

    @PostMapping(path = "/products/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Product createProduct(@Valid @RequestBody Product product){
        return productsService.createProduct(product);
    }
}
