package com.gconsentini.productsapi.controllers;

import com.gconsentini.productsapi.models.Product;
import com.gconsentini.productsapi.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProcuctsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/products")

    public ResponseEntity<List<Product>> getAllProducts(@RequestHeader("user-id") String userId){
        return new ResponseEntity<>(productsService.getAllProducts(userId), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id){
        return new ResponseEntity<>(productsService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/products/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
        return new ResponseEntity<>(productsService.createProduct(product), HttpStatus.OK);
    }
}
