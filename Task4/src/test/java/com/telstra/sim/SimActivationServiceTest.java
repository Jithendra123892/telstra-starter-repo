package com.telstra.sim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class SimActivationServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private SimActivationRepository repository;

    @InjectMocks
    private SimActivationService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void activateSim_shouldReturnSuccessActivation() {
        SimRequest request = new SimRequest("1234567890123456789", "test@example.com");

        ActuatorResponse actuatorResponse = new ActuatorResponse();
        actuatorResponse.setSuccess(true);
        actuatorResponse.setMessage("Activation successful");

        when(restTemplate.postForObject(
                any(String.class),
                any(),
                any()
        )).thenReturn(actuatorResponse);

        when(repository.save(any(SimActivation.class))).thenAnswer(invocation -> invocation.getArgument(0));

        SimActivation result = service.activateSim(request);

        assertNotNull(result);
        assertEquals("SUCCESS", result.getActivationStatus());
        assertEquals("Activation successful", result.getActivationMessage());
        assertEquals("1234567890123456789", result.getIccid());
        assertEquals("test@example.com", result.getCustomerEmail());
    }

    @Test
    void activateSim_shouldReturnFailureActivation() {
        SimRequest request = new SimRequest("9876543210123456789", "fail@example.com");

        ActuatorResponse actuatorResponse = new ActuatorResponse();
        actuatorResponse.setSuccess(false);
        actuatorResponse.setMessage("Activation failed");

        when(restTemplate.postForObject(
                any(String.class),
                any(),
                any()
        )).thenReturn(actuatorResponse);

        when(repository.save(any(SimActivation.class))).thenAnswer(invocation -> invocation.getArgument(0));

        SimActivation result = service.activateSim(request);

        assertNotNull(result);
        assertEquals("FAILURE", result.getActivationStatus());
        assertEquals("Activation failed", result.getActivationMessage());
        assertEquals("9876543210123456789", result.getIccid());
        assertEquals("fail@example.com", result.getCustomerEmail());
    }

    @Test
    void activateSim_shouldHandleNullResponseGracefully() {
        SimRequest request = new SimRequest("1111222233334444555", "null@response.com");

        when(restTemplate.postForObject(
                any(String.class),
                any(),
                any()
        )).thenReturn(null);

        when(repository.save(any(SimActivation.class))).thenAnswer(invocation -> invocation.getArgument(0));

        SimActivation result = service.activateSim(request);

        assertNotNull(result);
        assertEquals("FAILED", result.getActivationStatus());
        assertEquals("No response from actuator service", result.getActivationMessage());
        assertEquals("1111222233334444555", result.getIccid());
        assertEquals("null@response.com", result.getCustomerEmail());
    }
}
