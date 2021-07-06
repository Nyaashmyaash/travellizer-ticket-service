package com.nyash.travellizer.travellizerticketservice.repository;


import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Defines CRUD methods to access Ticket objects in the persistent storage
 *
 * @author Nyash
 *
 */
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    /**
     * Returns all the tickets for given trip
     * @param tripId
     * @return
     */

    List<Ticket>findByTripId(@Param("tripId") String tripId);
}
