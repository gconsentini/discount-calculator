package com.gconsentini.productsapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    private String id;
    @JsonProperty("price_in_cents")
    private int priceInCents;
    private String title;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(int priceInCents) {
        this.priceInCents = priceInCents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
