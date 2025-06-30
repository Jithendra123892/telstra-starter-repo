package com.telstra.sim;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ActuatorResponseTest {

    @Test
    void testGettersSetters() {
        ActuatorResponse response = new ActuatorResponse();
        response.setSuccess(true);
        response.setMessage("msg");

        assertTrue(response.isSuccess());
        assertEquals("msg", response.getMessage());
    }

    @Test
    void testConstructor() {
        ActuatorResponse response = new ActuatorResponse(true, "msg");
        assertTrue(response.isSuccess());
        assertEquals("msg", response.getMessage());
    }
}
