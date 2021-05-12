package com.gconsentini.productsapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Discount {

    private float percentage;
    @JsonProperty("value_in_cents")
    private int valueInCents;

    public Discount(float percentage, int valueInCents) {
        this.percentage = percentage;
        this.valueInCents = valueInCents;
    }
}
