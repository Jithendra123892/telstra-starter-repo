package com.telstra.sim;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
 class SimActivationRepositoryTest {

    @Autowired
    private SimActivationRepository repository;

    @Test
    void testSaveAndFind() {
        SimActivation activation = new SimActivation("111222333", "repo@test.com", "SUCCESS", "Saved");
        SimActivation saved = repository.save(activation);

        assertThat(saved.getId()).isNotNull();

        var found = repository.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getIccid()).isEqualTo("111222333");
    }
}
