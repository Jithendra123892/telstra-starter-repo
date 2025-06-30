package com.telstra.sim;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SimRequestTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldSetAndGetValues() {
        SimRequest request = new SimRequest();
        request.setIccid("123");
        request.setCustomerEmail("test@a.com");

        assertEquals("123", request.getIccid());
        assertEquals("test@a.com", request.getCustomerEmail());
    }

    @Test
    void shouldPassValidationForValidInput() {
        SimRequest request = new SimRequest("1234567890123456", "test@domain.com");
        Set<ConstraintViolation<SimRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailValidationForInvalidInput() {
        SimRequest request = new SimRequest("", "invalid-email");
        Set<ConstraintViolation<SimRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldBeEqualToSelf() {
        SimRequest request = new SimRequest("iccid", "mail@a.com");
        assertEquals(request, request);
    }

    @Test
    void shouldNotBeEqualToNull() {
        SimRequest request = new SimRequest("iccid", "mail@a.com");
        assertNotEquals(null , request); // ✅ Fixed order: expected, actual
    }

    @Test
    void shouldNotBeEqualToDifferentClass() {
        SimRequest request = new SimRequest("iccid", "mail@a.com");
        assertNotEquals("some string", request); // ✅ Fixed order: expected, actual
    }

    @Test
    void shouldNotBeEqualWithDifferentIccid() {
        SimRequest r1 = new SimRequest("111", "a@b.com");
        SimRequest r2 = new SimRequest("222", "a@b.com");
        assertNotEquals(r1, r2);
    }

    @Test
    void shouldNotBeEqualWithDifferentEmail() {
        SimRequest r1 = new SimRequest("111", "a@b.com");
        SimRequest r2 = new SimRequest("111", "c@d.com");
        assertNotEquals(r1, r2);
    }

    @Test
    void shouldBeEqualWhenFieldsMatch() {
        SimRequest expected = new SimRequest("abc", "x@y.com");
        SimRequest actual = new SimRequest("abc", "x@y.com");
        assertEquals(expected, actual);
    }

    @Test
    void shouldHandlePartialNullsInEquals() {
        SimRequest r1 = new SimRequest(null, "a@b.com");
        SimRequest r2 = new SimRequest("abc", "a@b.com");
        assertNotEquals(r1, r2);

        SimRequest r3 = new SimRequest("abc", null);
        SimRequest r4 = new SimRequest("abc", "a@b.com");
        assertNotEquals(r3, r4);
    }

    @Test
    void shouldBeEqualWhenBothFieldsNull() {
        SimRequest expected = new SimRequest(null, null);
        SimRequest actual = new SimRequest(null, null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotBeEqualWhenOnlyIccidIsNull() {
        SimRequest r1 = new SimRequest(null, "email@x.com");
        SimRequest r2 = new SimRequest("abc", "email@x.com");
        assertNotEquals(r1, r2);
    }

    @Test
    void shouldNotBeEqualWhenOnlyEmailIsNull() {
        SimRequest r1 = new SimRequest("abc", null);
        SimRequest r2 = new SimRequest("abc", "email@x.com");
        assertNotEquals(r1, r2);
    }

    @Test
    void shouldNotThrowExceptionWhenCallingHashCodeWithNulls() {
        SimRequest request = new SimRequest(null, null);
        assertDoesNotThrow(request::hashCode);
    }

    @Test
    void toStringShouldContainFieldValues() {
        SimRequest request = new SimRequest("iccid123", "email@x.com");
        String result = request.toString();
        assertTrue(result.contains("iccid123"));
        assertTrue(result.contains("email@x.com"));
    }

    @Test
    void toStringShouldHandleNullsGracefully() {
        SimRequest request = new SimRequest(null, null);
        String result = request.toString();
        assertTrue(result.contains("iccid='null'"));
        assertTrue(result.contains("customerEmail='null'"));
    }
}
