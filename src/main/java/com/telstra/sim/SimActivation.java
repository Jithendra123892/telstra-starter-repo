package com.telstra.sim;

import javax.persistence.*;

@Entity
public class SimActivation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String iccid;
    private String customerEmail;
    private boolean activated;

    // Constructors
    public SimActivation() {}

    public SimActivation(String iccid, String customerEmail, boolean activated) {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
        this.activated = activated;
    }

    // Getters and Setters
    public Long getId() {
        return id;
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

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
