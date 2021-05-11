package com.gconsentini.productsapi.controllers;

import com.gconsentini.productsapi.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class ProcuctsController {

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return Collections.EMPTY_LIST;
    }
}
