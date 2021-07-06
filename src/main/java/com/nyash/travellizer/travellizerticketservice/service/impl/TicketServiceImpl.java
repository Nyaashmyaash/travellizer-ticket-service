package com.nyash.travellizer.travellizerticketservice.service.impl;

import com.nyash.travellizer.common.generator.TicketNumberGenerator;
import com.nyash.travellizer.common.generator.text.StringGenerator;
import com.nyash.travellizer.common.infra.util.Checks;
import com.nyash.travellizer.model.entity.ticket.Order;
import com.nyash.travellizer.model.entity.ticket.Ticket;
import com.nyash.travellizer.persistence.ticket.OrderRepository;
import com.nyash.travellizer.persistence.ticket.TicketRepository;
import com.nyash.travellizer.service.ticket.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    private final OrderRepository orderRepository;

    /**
     * Default generator for ticket numbers
     */
    private final StringGenerator ticketNumberGenerator = new TicketNumberGenerator();

    @Override
    public List<Ticket> findTickets(String tripId) {
        return ticketRepository.findByTripId(tripId);
    }

    @Override
    public List<Order> findReservations(String tripId) {
        return orderRepository.findByTripId(tripId);
    }

    @Override
    public void makeReservation(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void cancelReservation(int orderId, String reason) {
        Optional<Order> order = orderRepository.findById(orderId);
        order.ifPresentOrElse(res -> {
            res.cancel(reason);
            orderRepository.save(res);
        }, () -> log.error("Invalid order identifier: {}", orderId));
    }

    @Override
    public void completeReservation(int orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        order.ifPresentOrElse(res -> {
            res.complete();
            orderRepository.save(res);
        }, () -> log.error("Invalid order identifier: {}", orderId));
    }

    @Override
    public Ticket buyTicket(String tripId, String clientName) {
        Checks.checkParameter(tripId != null, "Trip identifier should be not null");

        Ticket ticket = new Ticket();
        ticket.setTripId(tripId);
        ticket.generateUid(ticketNumberGenerator);
        ticket.setName(clientName);
        ticketRepository.save(ticket);

        return ticket;
    }

    @Override
    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findOrders(String userId) {
        return orderRepository.findByCreatedBy(userId);
    }
}
