package com.telstra.sim;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SimActivationController.class)
public class SimActivationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SimActivationService simActivationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void activateSim_shouldReturnOk() throws Exception {
        SimRequest request = new SimRequest();
        request.setIccid("123456789");
        request.setCustomerEmail("test@example.com");

        mockMvc.perform(post("/api/activate")  // ensure path matches controller!
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
