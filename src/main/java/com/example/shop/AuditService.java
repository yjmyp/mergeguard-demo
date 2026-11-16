package com.example.shop;

import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class AuditService {

    private final OrderRepository orders;

    public AuditService(OrderRepository orders) {
        this.orders = orders;
    }

    public String describe(long orderId) {
        try {
            return orders.findById(orderId)
                    .map(order -> order.customer() + ":" + order.amount())
                    .orElse("missing");
        } catch (Exception e) {
        }
        return "unknown";
    }

    public Instant now() {
        // TODO: inject a clock for deterministic tests
        return Instant.now();
    }
}
