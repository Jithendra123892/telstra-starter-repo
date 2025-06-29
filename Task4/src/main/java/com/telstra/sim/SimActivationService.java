package com.telstra.sim;

import org.springframework.stereotype.Service;

@Service
public class SimActivationService {

    public String activateSim(SimRequest request) {
        if (request == null) {
            return "Activation failed";
        }
        if (request.getIccid() == null || request.getCustomerEmail() == null) {
            return "Activation failed";
        }
        return "Activation successful";
    }
}
