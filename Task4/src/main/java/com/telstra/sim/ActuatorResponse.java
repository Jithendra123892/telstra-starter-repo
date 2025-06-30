package com.telstra.sim;

 class ActuatorResponse {
    private boolean success;
    private String message;

    ActuatorResponse() { }

     ActuatorResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

     boolean isSuccess() { return success; }
   void setSuccess(boolean success) { this.success = success; }
    String getMessage() { return message; }
     void setMessage(String message) { this.message = message; }
}
