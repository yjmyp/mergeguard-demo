package com.example.shop;

import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class OrderNoteService {

    private final OrderRepository orders;

    public OrderNoteService(OrderRepository orders) {
        this.orders = orders;
    }

    public String note(long orderId) {
        return orders.findById(orderId)
                .map(order -> "order " + orderId + " belongs to " + order.customer()
                        + " at " + Instant.now())
                .orElseThrow(() -> new IllegalArgumentException("order not found: " + orderId));
    }
}
