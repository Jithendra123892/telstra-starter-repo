package com.telstra.sim;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ErrorResponseTest {

    @Test
    void testErrorMessageGetterSetter() {
        ErrorResponse error = new ErrorResponse();
        error.setErrorMessage("Something went wrong");
        assertEquals("Something went wrong", error.getErrorMessage());
    }

    @Test
    void testErrorMessageConstructor() {
        ErrorResponse error = new ErrorResponse("Custom error");
        assertEquals("Custom error", error.getErrorMessage());
    }
}
