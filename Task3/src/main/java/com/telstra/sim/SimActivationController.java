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
    private SimActivationRepository simActivationRepository;

    public SimActivationController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @PostMapping("/activate")
    public ResponseEntity<String> activateSim(@RequestBody SimRequest simRequest) {
        String actuatorUrl = "http://localhost:8444/actuate";

        Map<String, String> payload = new HashMap<>();
        payload.put("iccid", simRequest.getIccid());

        try {
            ResponseEntity<ActuatorResponse> response = restTemplate.postForEntity(
                    actuatorUrl,
                    payload,
                    ActuatorResponse.class
            );

            boolean success = response.getBody() != null && response.getBody().isSuccess();

            // Save record to DB
            SimActivation simActivation = new SimActivation();
            simActivation.setIccid(simRequest.getIccid());
            simActivation.setCustomerEmail(simRequest.getCustomerEmail());
            simActivation.setActive(success);
            simActivationRepository.save(simActivation);

            if (success) {
                return ResponseEntity.ok("Activation successful");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Activation failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error contacting actuator");
        }
    }

    @GetMapping("/status")
    public ResponseEntity<?> getSimStatus(@RequestParam Long simCardId) {
        Optional<SimActivation> record = simActivationRepository.findById(simCardId);
        if (record.isPresent()) {
            SimActivation sim = record.get();
            Map<String, Object> response = new HashMap<>();
            response.put("iccid", sim.getIccid());
            response.put("customerEmail", sim.getCustomerEmail());
            response.put("active", sim.isActive());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("SIM record not found");
        }
    }
}
