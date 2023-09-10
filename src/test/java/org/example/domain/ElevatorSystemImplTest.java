package org.example.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorSystemImplTest {

    private ElevatorSystem elevatorSystem;

    @BeforeEach
    void setUp() {
        this.elevatorSystem = new ElevatorSystemImpl();
    }

    @Test
    @DisplayName("Test addRequest")
    void testAddRequest() {
        elevatorSystem.addRequest(new ElevatorRequest(1, 2, ElevatorState.UP));
    }

    @Test
    @DisplayName("Test addRequest for multiple requests")
    void testAddRequestForMultipleRequests() {
        elevatorSystem.addRequest(new ElevatorRequest(1, 2, ElevatorState.UP));
        elevatorSystem.addRequest(new ElevatorRequest(1, 3, ElevatorState.UP));
        elevatorSystem.addRequest(new ElevatorRequest(1, 4, ElevatorState.UP));
        elevatorSystem.addRequest(new ElevatorRequest(10, 5, ElevatorState.DOWN));
    }

}