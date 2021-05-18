package com.gconsentini.productsapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class Product {

    private String id;
    @JsonProperty("price_in_cents")
    @NotNull(message = "Preço não pode ser vazio")
    private int priceInCents;
    @NotEmpty(message = "Titulo não pode ser vazio")
    private String title;
    @NotEmpty(message = "Descricao não pode ser vazio")
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
