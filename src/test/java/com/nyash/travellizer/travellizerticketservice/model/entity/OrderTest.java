package com.nyash.travellizer.travellizerticketservice.model.entity;

import com.nyash.travellizer.travellizerticketservice.model.Order;
import com.nyash.travellizer.travellizerticketservice.model.OrderState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    Order order;

    @BeforeEach
    void setup() {
        order = new Order();
        order.setId(1);
        order.setDueDate(LocalDateTime.now().plusDays(2));
    }

    @Test
    void complete_validOrder_orderCompleted() {
        order.complete();
        assertEquals(order.getState(), OrderState.COMPLETED);
        assertTrue(order.isCompleted());
    }

    @Test
    void complete_expiredOrder_exceptionThrown() {
        order.setDueDate(LocalDateTime.now().minusDays(1));
        assertThrows(Exception.class, () -> order.complete());
    }

    @Test
    void cancel_validOrder_orderCancelled() {
        order.cancel("test");
        assertEquals(order.getState(), OrderState.CANCELLED);
        assertTrue(order.isCancelled());
        assertEquals(order.getCancellationReason(), "test");
    }
}
