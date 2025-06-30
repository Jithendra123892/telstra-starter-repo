package com.telstra.sim;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
 class SimActivationService {

    private final RestTemplate restTemplate;
    private final SimActivationRepository simActivationRepository;

     SimActivationService(RestTemplate restTemplate, SimActivationRepository simActivationRepository) {
        this.restTemplate = restTemplate;
        this.simActivationRepository = simActivationRepository;
    }

     SimActivation activateSim(SimRequest request) {
        ActuatorResponse response = restTemplate.postForObject(
                "http://localhost:8444/actuate",
                request.getIccid(),
                ActuatorResponse.class
        );

        String status = "FAILED";
        String message = "No response from actuator service";

        if (response != null) {
            status = response.isSuccess() ? "SUCCESS" : "FAILURE";
            message = response.getMessage();
        }

        SimActivation activation = new SimActivation(
                request.getIccid(),
                request.getCustomerEmail(),
                status,
                message
        );

        simActivationRepository.save(activation);
        return activation;
    }
}
