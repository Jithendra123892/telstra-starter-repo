package com.telstra.sim;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimActivationTest {

    @Test
    void testSimActivationGettersAndSetters() {
        SimActivation sim = new SimActivation();
        sim.setId(1L);
        sim.setIccid("123456789");
        sim.setCustomerEmail("test@example.com");
        sim.setStatus("Activated");

        assertEquals(1L, sim.getId());
        assertEquals("123456789", sim.getIccid());
        assertEquals("test@example.com", sim.getCustomerEmail());
        assertEquals("Activated", sim.getStatus());
    }
}
