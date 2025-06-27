package com.telstra.sim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/sim")
public class SimActivationController {

    private final RestTemplate restTemplate;

    @Autowired
    private SimActivationRepository repository;

    public SimActivationController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    // POST request to activate a SIM
    @PostMapping("/activate")
    public ResponseEntity<String> activateSim(@RequestBody SimRequest simRequest) {
        String actuatorUrl = "http://localhost:8444/actuate";

        Map<String, String> payload = new HashMap<>();
        payload.put("iccid", simRequest.getIccid());

        try {
            ResponseEntity<ActuatorResponse> response = restTemplate.postForEntity(
                    actuatorUrl, payload, ActuatorResponse.class);

            boolean success = response.getBody() != null && response.getBody().isSuccess();

            // Save the activation result to database
            SimActivation record = new SimActivation(
                    simRequest.getIccid(),
                    simRequest.getCustomerEmail(),
                    success
            );
            repository.save(record);

            if (success) {
                System.out.println("Activation successful");
                return ResponseEntity.ok("Activation successful");
            } else {
                System.out.println("Activation failed");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Activation failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error contacting actuator");
        }
    }

    // GET request to fetch all activation records
    @GetMapping("/all")
    public List<SimActivation> getAllActivations() {
        return repository.findAll();
    }
}
