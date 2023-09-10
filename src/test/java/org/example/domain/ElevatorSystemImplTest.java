package org.example.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("Test for less requests than elevators")
    void testAddRequestForMultipleRequests() {
        for (int i = 0; i < 5; i++) {
            elevatorSystem.addRequest(new ElevatorRequest(1, 20, ElevatorState.UP));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 10, 15})
    @DisplayName("Test for more requests than elevators")
    void testForMultipleRequests(int numberOfRequests) {
        for (int i = 0; i < numberOfRequests; i++) {
            elevatorSystem.addRequest(new ElevatorRequest(1, 20, ElevatorState.UP));
        }
    }

}