package com.example.shop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class OrderNoteServiceTest {

    private final OrderRepository repository = mock(OrderRepository.class);
    private final OrderNoteService service = new OrderNoteService(repository);

    @Test
    void notesExistingOrder() {
        when(repository.findById(1L)).thenReturn(Optional.of(new Order("bob", new BigDecimal("3"))));
        assertThat(service.note(1L)).contains("bob");
    }

    @Test
    void rejectsMissingOrder() {
        when(repository.findById(2L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.note(2L)).isInstanceOf(IllegalArgumentException.class);
    }
}
