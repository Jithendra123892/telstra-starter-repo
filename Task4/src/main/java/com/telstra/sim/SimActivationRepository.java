package com.telstra.sim;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
 interface SimActivationRepository extends JpaRepository<SimActivation, Long> {
    // Optionally, add custom queries here if needed
}
