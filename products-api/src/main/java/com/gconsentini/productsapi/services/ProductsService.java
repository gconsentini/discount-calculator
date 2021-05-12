package com.gconsentini.productsapi.services;

import com.gconsentini.productsapi.ProductOuterClass;
import com.gconsentini.productsapi.ProductServiceGrpc;
import com.gconsentini.productsapi.models.Product;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    @GrpcClient("product-server")
    private ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub;

    public List<Product> getAllProducts(){
        ProductOuterClass.ListProductsResponse response = productServiceBlockingStub.listProducts(
                ProductOuterClass.ListProductsRequest.newBuilder().build()
        );
        return response.getProductsList().stream()
                .map(this::getProductByProductGrpc)
                .collect(Collectors.toList());
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

    private Product getProductByProductGrpc(ProductOuterClass.Product product){
        return Product.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .priceInCents(product.getPriceInCents())
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