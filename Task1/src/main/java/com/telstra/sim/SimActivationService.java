package com.telstra.sim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class SimActivationService {

    @Autowired
    private SimActivationRepository simActivationRepository;

    public String activateSim(SimRequest simRequest) {
        try {
            URL url = new URL("http://localhost:8444/actuate");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            String jsonInputString = String.format(
                    "{\"simId\":\"%s\", \"iccid\":\"%s\", \"customerEmail\":\"%s\"}",
                    simRequest.getSimId(), simRequest.getIccid(), simRequest.getCustomerEmail());

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int code = con.getResponseCode();
            boolean isActivated = false;

            if (code == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line.trim());
                }

                // Simple string parsing to check status
                isActivated = response.toString().contains("ACTIVATED");
            }

            // Save record in DB
            SimActivation sim = new SimActivation();
            sim.setIccid(simRequest.getIccid());
            sim.setCustomerEmail(simRequest.getCustomerEmail());
            sim.setActive(isActivated);
            simActivationRepository.save(sim);

            return isActivated ? "SIM Activated and saved." : "SIM Activation failed but saved.";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred during activation.";
        }
    }
}
