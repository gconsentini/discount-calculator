package com.gconsentini.productsapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class Product {

    private String id;
    @JsonProperty("price_in_cents")
    @NotBlank(message = "preço não pode ser vazio")
    private int priceInCents;
    @NotBlank(message = "titulo não pode ser vazio")
    private String title;
    @NotBlank(message = "descrição não pode ser vazia")
    private String description;
    private Discount discount;

    public Product(){

    }

    public Product(String id, int priceInCents, String title, String description, Discount discount) {
        this.id = id;
        this.priceInCents = priceInCents;
        this.title = title;
        this.description = description;
        this.discount = discount;
    }

}
