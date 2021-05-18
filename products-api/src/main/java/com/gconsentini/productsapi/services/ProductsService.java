package com.gconsentini.productsapi.services;

import com.gconsentini.productsapi.Calculator;
import com.gconsentini.productsapi.CalculatorServiceGrpc;
import com.gconsentini.productsapi.ProductOuterClass;
import com.gconsentini.productsapi.ProductServiceGrpc;
import com.gconsentini.productsapi.models.Discount;
import com.gconsentini.productsapi.models.Product;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    @GrpcClient("product-server")
    private ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub;

    @GrpcClient("calculator-server")
    private CalculatorServiceGrpc.CalculatorServiceBlockingStub calculatorServiceBlockingStub;

    public List<Product> getAllProducts(String userId){
        ProductOuterClass.ListProductsResponse response = productServiceBlockingStub.listProducts(
                ProductOuterClass.ListProductsRequest.newBuilder().build()
        );
        List<Product> responseList = response.getProductsList().stream()
                .map(p -> getProductByProductGrpc(p, userId))
                .collect(Collectors.toList());

        return responseList;
    }

    public Product createProduct(Product productParams){
        ProductOuterClass.ProductResponse response = productServiceBlockingStub.product(
                ProductOuterClass.ProductRequest.newBuilder().setProduct(
                        ProductOuterClass.Product.newBuilder()
                                .setDescription(productParams.getDescription())
                                .setTitle(productParams.getTitle())
                                .setPriceInCents(productParams.getPriceInCents())
                                .build()
                ).build()
        );
        return getProductByProductResponse(response);
    }

    public Product getProductById(String id){
        ProductOuterClass.ProductResponse response = productServiceBlockingStub.getProductById(
                ProductOuterClass.GetProductByIdRequest.newBuilder().setId(id).build()
        );

        return getProductByProductResponse(response);
    }

    private Product getProductByProductGrpc(ProductOuterClass.Product product, String userId){
        Calculator.ProductDiscountResponse response;
        try {
            response = calculatorServiceBlockingStub.getProductDiscountByUserId(
                    Calculator.DiscountRequest.newBuilder()
                            .setProductId(product.getId())
                            .setUserId(userId)
                            .build()
            );
        } catch (Exception e){
            System.out.println("Error accessing the calculator api");
            return Product.builder()
                    .id(product.getId())
                    .title(product.getTitle())
                    .description(product.getDescription())
                    .priceInCents(product.getPriceInCents())
                    .build();
        }

        return Product.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .priceInCents(product.getPriceInCents())
                .discount(Discount.builder()
                        .percentage(response.getDiscount().getPercentage())
                        .valueInCents(response.getDiscount().getValueInCents())
                        .build())
                .build();
    }

    private Product getProductByProductResponse(ProductOuterClass.ProductResponse productResponse){
        return Product.builder()
                .id(productResponse.getProduct().getId())
                .title(productResponse.getProduct().getTitle())
                .description(productResponse.getProduct().getDescription())
                .priceInCents(productResponse.getProduct().getPriceInCents())
                .build();
    }
}