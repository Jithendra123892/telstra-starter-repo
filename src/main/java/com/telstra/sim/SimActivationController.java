package com.telstra.sim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/sim")
public class SimActivationController {

    @Autowired
    private SimActivationService simActivationService;

    @Autowired
    private SimActivationRepository simActivationRepository;

    @PostMapping("/activate")
    public String activateSim(@RequestBody SimRequest simRequest) {
        return simActivationService.activateSim(simRequest);
    }

    @GetMapping("/query")
    public ResponseEntity<?> getSimDetails(@RequestParam Long simCardId) {
        Optional<SimActivation> sim = simActivationRepository.findById(simCardId);
        if (sim.isPresent()) {
            SimActivation simActivation = sim.get();
            Map<String, Object> response = new HashMap<>();
            response.put("iccid", simActivation.getIccid());
            response.put("customerEmail", simActivation.getCustomerEmail());
            response.put("active", simActivation.isActive());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("SIM not found.");
        }
    }
}
