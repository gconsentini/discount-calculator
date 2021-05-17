package com.gconsentini.productsapi.services;

import com.gconsentini.productsapi.CalculatorServiceGrpc;
import com.gconsentini.productsapi.ProductOuterClass;
import com.gconsentini.productsapi.ProductServiceGrpc;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductsServiceTest {

    @Autowired
    private ProductsService productsService;

    @Mock
    private ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub;

    @Mock
    private CalculatorServiceGrpc.CalculatorServiceBlockingStub calculatorServiceBlockingStub;

    @Test
    public void getAllProductsTest(){
        String userId = "id";

        when(productServiceBlockingStub.listProducts(any())).thenReturn(ProductOuterClass.ListProductsResponse.newBuilder().build());

        assert (productsService.getAllProducts(userId)).isEmpty();
    }
}