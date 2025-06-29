package com.telstra.sim;

public class SimRequest {
    private String iccid;
    private String customerEmail;

    public SimRequest() {
        // No-arg constructor for frameworks and tests
    }

    public SimRequest(String iccid, String customerEmail) {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
