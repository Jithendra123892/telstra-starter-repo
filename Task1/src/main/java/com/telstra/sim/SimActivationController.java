package com.telstra.sim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sim")
public class SimActivationController {

    private final RestTemplate restTemplate;

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
}
