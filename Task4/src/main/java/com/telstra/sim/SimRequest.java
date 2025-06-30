package com.telstra.sim;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class SimRequest {

    @NotBlank
    private String iccid;

    @NotBlank
    @Email
    private String customerEmail;

     SimRequest() {
    }

    SimRequest(String iccid, String customerEmail) {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
    }

     String getIccid() {
        return iccid;
    }

     void setIccid(String iccid) {
        this.iccid = iccid;
    }

    String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimRequest)) return false;
        SimRequest that = (SimRequest) o;
        return Objects.equals(iccid, that.iccid) &&
                Objects.equals(customerEmail, that.customerEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iccid, customerEmail);
    }

    @Override
    public String toString() {
        return "SimRequest{" +
                "iccid='" + iccid + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                '}';
    }
}
