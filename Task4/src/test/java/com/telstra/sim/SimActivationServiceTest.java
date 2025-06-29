package com.telstra.sim;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimActivationServiceTest {

    SimActivationService service = new SimActivationService();

    @Test
    void shouldReturnSuccessForValidRequest() {
        SimRequest request = new SimRequest("123456789", "test@example.com");
        String result = service.activateSim(request);
        assertEquals("Activation successful", result);
    }

    @Test
    void shouldReturnFailForNullRequest() {
        String result = service.activateSim(null);
        assertEquals("Activation failed", result);
    }

    @Test
    void shouldReturnFailForNullIccid() {
        SimRequest request = new SimRequest(null, "test@example.com");
        String result = service.activateSim(request);
        assertEquals("Activation failed", result);
    }

    @Test
    void shouldReturnFailForNullEmail() {
        SimRequest request = new SimRequest("123456789", null);
        String result = service.activateSim(request);
        assertEquals("Activation failed", result);
    }

    @Test
    void shouldReturnFailForBothFieldsNull() {
        SimRequest request = new SimRequest(null, null);
        String result = service.activateSim(request);
        assertEquals("Activation failed", result);
    }
}
