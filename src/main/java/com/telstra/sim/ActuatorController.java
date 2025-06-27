package com.telstra.sim;

import org.springframework.web.bind.annotation.*;

@RestController
public class ActuatorController {

    @PostMapping("/actuate")
    public ActuatorResponse activateSim(@RequestBody SimRequest request) {
        ActuatorResponse response = new ActuatorResponse();
        response.setStatus("ACTIVATED"); // String value
        return response;
    }
}
