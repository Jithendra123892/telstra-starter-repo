package com.telstra.sim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class SimActivationController {

    @Autowired
    private SimActivationService simActivationService;

    @PostMapping("/activate")
    public ResponseEntity<String> activateSim(@RequestBody SimRequest request) {
        String result = simActivationService.activateSim(request);
        return ResponseEntity.ok(result);
    }
}
