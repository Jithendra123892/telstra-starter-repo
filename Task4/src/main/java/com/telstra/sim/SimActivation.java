package com.telstra.sim;

import jakarta.persistence.*;

@Entity
@Table(name = "sim_activation")
class SimActivation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String iccid;

    @Column(nullable = false)
    private String customerEmail;

    @Column(nullable = false)
    private String activationStatus;

    @Column(nullable = false)
    private String activationMessage;

     SimActivation() { }

    SimActivation(String iccid, String customerEmail, String activationStatus, String activationMessage) {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
        this.activationStatus = activationStatus;
        this.activationMessage = activationMessage;
    }

    Long getId() { return id; }
     String getIccid() { return iccid; }
    String getCustomerEmail() { return customerEmail; }
     String getActivationStatus() { return activationStatus; }
    String getActivationMessage() { return activationMessage; }
}
