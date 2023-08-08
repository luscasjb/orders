package com.store.orders.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusOrder {

    CREATED("created"),
    COMPLETED("completed"),
    INCOMPLETE("incomplete"),
    ABANDONED("abandoned"),
    UPDATED("updated");

    private String value;
    StatusOrder(String value){
        this.value = value;
    };
    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static StatusOrder fromValue(String value) {
        for (StatusOrder b : StatusOrder.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}