package stepDefinitions;

import io.cucumber.java.en.*;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class SimCardActivatorStepDefinitions {

    private final TestRestTemplate restTemplate = new TestRestTemplate();
    private ResponseEntity<String> response;
    private long currentId = 1;

    private String iccid;
    private String email;

    @Given("a SIM activation request with ICCID {string} and email {string}")
    public void sim_activation_request(String iccid, String email) {
        this.iccid = iccid;
        this.email = email;
    }

    @When("the request is submitted to the activation endpoint")
    public void submit_request() {
        String url = "http://localhost:8080/sim/activate";
        Map<String, String> body = new HashMap<>();
        body.put("iccid", iccid);
        body.put("customerEmail", email);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
        response = restTemplate.postForEntity(url, request, String.class);
    }

    @Then("the activation should be successful")
    public void verify_successful_activation() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Then("the activation should fail")
    public void verify_failed_activation() {
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @And("the SIM card status for ID {int} should be active")
    public void verify_status_active(int id) {
        String url = "http://localhost:8080/sim/status?simCardId=" + id;
        ResponseEntity<String> statusResponse = restTemplate.getForEntity(url, String.class);
        assertTrue(statusResponse.getBody().contains("\"active\":true"));
    }

    @And("the SIM card status for ID {int} should be inactive")
    public void verify_status_inactive(int id) {
        String url = "http://localhost:8080/sim/status?simCardId=" + id;
        ResponseEntity<String> statusResponse = restTemplate.getForEntity(url, String.class);
        assertTrue(statusResponse.getBody().contains("\"active\":false"));
    }
}
