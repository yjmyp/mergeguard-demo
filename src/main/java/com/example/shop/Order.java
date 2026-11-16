package com.example.shop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private String customer;
    private BigDecimal amount;

    protected Order() {
    }

    public Order(String customer, BigDecimal amount) {
        this.customer = customer;
        this.amount = amount;
    }

    public BigDecimal amount() { return amount; }
    public String customer() { return customer; }
}
