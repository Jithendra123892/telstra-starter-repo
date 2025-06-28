Feature: SIM Activation

  Scenario: Successful SIM card activation
    Given a SIM activation request with ICCID "1255789453849037777" and email "success@example.com"
    When the request is submitted to the activation endpoint
    Then the activation should be successful
    And the SIM card status for ID 1 should be active

  Scenario: Failed SIM card activation
    Given a SIM activation request with ICCID "8944500102198304826" and email "fail@example.com"
    When the request is submitted to the activation endpoint
    Then the activation should fail
    And the SIM card status for ID 2 should be inactive
