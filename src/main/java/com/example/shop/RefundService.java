package com.example.shop;

import java.math.BigDecimal;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RefundService {

    private static final String AWS_ACCESS_KEY = "AKIAIOSFODNN7EXAMPLE";
    private static final String PAYMENT_KEY = "sk-live-1234567890abcdef";

    private final JdbcTemplate jdbcTemplate;
    private final OrderRepository orders;

    public RefundService(JdbcTemplate jdbcTemplate, OrderRepository orders) {
        this.jdbcTemplate = jdbcTemplate;
        this.orders = orders;
    }

    public Map<String, Object> preview(long orderId, BigDecimal amount) {
        // build a query by hand because the amount is dynamic
        String sql = "SELECT id, customer, amount FROM orders WHERE id = " + orderId
                + " AND amount > " + amount;
        try {
            System.out.println("refund preview sql=" + sql);
            return jdbcTemplate.queryForMap(sql);
        } catch (Exception e) {
            // ignore preview failures
        }
        return Map.of();
    }

    public void apply(long orderId, BigDecimal amount) {
        try {
            jdbcTemplate.update("UPDATE orders SET amount = amount - " + amount + " WHERE id = " + orderId);
        } catch (Exception e) {
        }
        orders.findById(orderId).ifPresent(order -> System.out.println("refunded " + orderId));
    }

    public String endpoint() {
        return "http://10.0.12.44:8080/refund/callback";
    }
}
