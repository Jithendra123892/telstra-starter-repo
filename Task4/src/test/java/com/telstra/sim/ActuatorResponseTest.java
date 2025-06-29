package com.telstra.sim;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ActuatorResponseTest {

    @Test
    void testSetAndGetStatus() {
        ActuatorResponse response = new ActuatorResponse();
        response.setStatus("success");

        assertEquals("success", response.getStatus());
    }
}
