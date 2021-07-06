package com.nyash.travellizer.travellizerticketservice.repository;


import com.nyash.travellizer.travellizerticketservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Defines CRUD methods to access Order objects in the persistent storage
 *
 * @author Nyash
 *
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {

    /**
     * Returns all the orders for given trip
     * @param tripId
     * @return
     */
    List<Order> findByTripId(@Param("tripId") String tripId);

    /**
     * Returns all the orders for given user
     * @param userId
     * @return
     */
    List<Order>findByCreatedBy(String userId);


}
