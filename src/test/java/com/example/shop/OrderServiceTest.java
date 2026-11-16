package com.example.shop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

    private final OrderRepository repository = mock(OrderRepository.class);
    private final OrderService service = new OrderService(repository);

    @Test
    void rejectsZeroAmount() {
        assertThatThrownBy(() -> service.create("alice", BigDecimal.ZERO))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void sumsOrders() {
        when(repository.findByCustomer("alice"))
                .thenReturn(List.of(new Order("alice", new BigDecimal("10")),
                        new Order("alice", new BigDecimal("5"))));
        assertThat(service.totalFor("alice")).isEqualByComparingTo("15");
    }
}
