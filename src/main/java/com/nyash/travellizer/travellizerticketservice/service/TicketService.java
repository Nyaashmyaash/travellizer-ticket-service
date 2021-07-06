package com.nyash.travellizer.travellizerticketservice.service;

import com.nyash.travellizer.model.entity.ticket.Order;
import com.nyash.travellizer.model.entity.ticket.Ticket;

import java.util.List;

public interface TicketService {

    /**
     * Returns all the tickets for the specified trip
     *
     * @param tripId
     * @return
     */
    List<Ticket> findTickets(String tripId);

    /**
     * Returns all the reservations for the specified trip.
     * @param tripId
     * @return
     */
    List<Order> findReservations(String tripId);

    /**
     * Puts an order
     */
    void makeReservation(Order order);

    /**
     * Cancels ticket reservation with specified identifier
     * @param orderId
     */
    void cancelReservation(int orderId, String reason);

    /**
     * Completes reservation and purchases a ticket
     * @param orderId
     */
    void completeReservation(int orderId);

    /**
     * Purchases a ticket for the specified trip
     * @param tripId
     * @param clientName
     */
    Ticket buyTicket(String tripId, String clientName);

    /**
     * Returns all the orders
     * @return
     */
    List<Order> findOrders();

    /**
     * Returns orders of specific user
     * @param userId
     * @return
     */
    List<Order> findOrders (String userId);
}
