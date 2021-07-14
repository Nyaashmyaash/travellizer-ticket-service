package com.nyash.travellizer.travellizerticketservice.model.entity;

import com.nyash.travellizer.travellizercommon.generator.TicketNumberGenerator;
import com.nyash.travellizer.travellizercommon.generator.text.StringGenerator;
import com.nyash.travellizer.travellizercommon.infra.exception.flow.InvalidParameterException;
import com.nyash.travellizer.travellizerticketservice.model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicketTest {

    private Ticket ticket;

    @BeforeEach
    void setup() {
        ticket = new Ticket();
        ticket.setId(1);
    }

    @Test
    void generateUid_validGenerator_uidAssigned() {
        StringGenerator generator = new TicketNumberGenerator();
        ticket.generateUid(generator);

        assertNotNull(ticket.getUid());
        assertEquals(Ticket.TICKET_NUMBER_SIZE, ticket.getUid().length());
    }

    @Test
    void generateUid_nullGenerator_exceptionThrow() {
        assertThrows(InvalidParameterException.class, () -> ticket.generateUid(null));
    }
}
