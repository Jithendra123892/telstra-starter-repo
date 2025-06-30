package com.telstra.sim;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sim")
 class SimActivationController {

    private final SimActivationService service;
    private final SimActivationRepository repository;

     SimActivationController(SimActivationService service, SimActivationRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping("/activate")
    ResponseEntity<SimActivation> activateSim(@RequestBody SimRequest request) {
        SimActivation activation = service.activateSim(request);
        return ResponseEntity.ok(activation);
    }

    @GetMapping("/all")
    List<SimActivation> getAllActivations() {
        return repository.findAll();
    }
}
