package com.bobsusedbooks.enums;

public enum OrderStatus {
    PENDING(1),
    PROCESSING(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELLED(5);
    
    private final int value;
    
    OrderStatus(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public static OrderStatus fromValue(int value) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus value: " + value);
    }
}
