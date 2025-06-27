package com.telstra.sim;

public class ActuatorResponse {
    private boolean success;

    // Constructors
    public ActuatorResponse() {}

    public ActuatorResponse(boolean success) {
        this.success = success;
    }

    // Getter and Setter
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
