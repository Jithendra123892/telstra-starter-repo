package com.telstra.sim;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimActivationRepository extends JpaRepository<SimActivation, Long> {
}
