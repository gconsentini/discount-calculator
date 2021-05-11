package com.gconsentini.productsapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Discount {

    private float percentage;
    @JsonProperty("value_in_cents")
    private int valueInCents;

    public Discount(float percentage, int valueInCents) {
        this.percentage = percentage;
        this.valueInCents = valueInCents;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getValueInCents() {
        return valueInCents;
    }

    public void setValueInCents(int valueInCents) {
        this.valueInCents = valueInCents;
    }
}
