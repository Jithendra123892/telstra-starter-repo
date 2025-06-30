package com.telstra.sim;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

 class TelstraApplicationTest {

    @Test
    void mainMethod_runsWithoutExceptions() {
        assertDoesNotThrow(() -> TelstraApplication.main(new String[]{}));
    }
}
