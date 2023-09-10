package org.example.domain.policy;

import org.example.domain.Elevator;
import org.example.domain.ElevatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IdleElevatorTest {

    private Elevator[] elevators;

    @BeforeEach
    void setUp() {
        this.elevators = new Elevator[]{new ElevatorImpl(), new ElevatorImpl()};
    }

    @Test
    @DisplayName("Find first idle elevator when all are idle")
    void testFindFirstIdleElevator() {
        Optional<Elevator> optionalElevator = new IdleElevator().findElevator(elevators);
        assertTrue(optionalElevator.isPresent());
    }

    @Test
    @DisplayName("Find first idle elevator when some are idle")
    void testFindFirstIdleElevatorSomeIdle() {
        Thread thread = new Thread(() -> elevators[0].move(10));
        thread.start();
        Optional<Elevator> optionalElevator = new IdleElevator().findElevator(elevators);
        assertTrue(optionalElevator.isPresent());
    }

    @Test
    @DisplayName("Find no idle elevator when none are idle")
    void testFindNoIdleElevator() {
        Thread thread1 = new Thread(() -> elevators[0].move(10));
        thread1.start();
        Thread thread2 = new Thread(() -> elevators[1].move(10));
        thread2.start();

        Optional<Elevator> optionalElevator = new IdleElevator().findElevator(elevators);
        assertFalse(optionalElevator.isPresent());
    }

}