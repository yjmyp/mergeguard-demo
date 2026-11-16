package com.example.shop;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order create(String customer, BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("amount must be positive");
        }
        return repository.save(new Order(customer, amount));
    }

    public List<Order> findByCustomer(String customer) {
        return repository.findByCustomer(customer);
    }

    public BigDecimal totalFor(String customer) {
        return findByCustomer(customer).stream()
                .map(Order::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
