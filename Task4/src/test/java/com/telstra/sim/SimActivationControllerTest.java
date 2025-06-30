package com.telstra.sim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class SimActivationControllerTest {

    @Mock
    private SimActivationService service;

    @Mock
    private SimActivationRepository repository;

    @InjectMocks
    private SimActivationController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void activateSim_shouldReturnActivation() {
        SimRequest request = new SimRequest("1234567890", "test@example.com");
        SimActivation expected = new SimActivation("1234567890", "test@example.com", "SUCCESS", "Activated");

        when(service.activateSim(request)).thenReturn(expected);

        ResponseEntity<SimActivation> response = controller.activateSim(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    void getAllActivations_shouldReturnList() {
        SimActivation record1 = new SimActivation("iccid1", "email1@telstra.com", "SUCCESS", "OK");
        SimActivation record2 = new SimActivation("iccid2", "email2@telstra.com", "FAILED", "Error");

        when(repository.findAll()).thenReturn(Arrays.asList(record1, record2));

        List<SimActivation> result = controller.getAllActivations();

        assertEquals(2, result.size());
        assertTrue(result.contains(record1));
        assertTrue(result.contains(record2));
    }
}
