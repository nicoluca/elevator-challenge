package org.example.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElevatorImplTest {

    private Elevator elevator;

    @BeforeEach
    void setUp() {
        elevator = new ElevatorImpl();
    }

    @Test
    @DisplayName("Floor zero for new elevator")
    void testGetCurrentFloor() {
        int currentFloor = elevator.getCurrentFloor();
        assertEquals(0, currentFloor);
    }

    @Test
    @DisplayName("New elevator is idle")
    void testIsMoving() {
        assertEquals(ElevatorState.IDLE, elevator.getState());
    }

    @Test
    @DisplayName("Elevator moves up")
    void testMoveUp() {
        elevator.move(1);
        assertEquals(1, elevator.getCurrentFloor());
    }

    @Test
    @DisplayName("Elevator moves down")
    void testMoveDown() {
        elevator.move(-1);
        assertEquals(-1, elevator.getCurrentFloor());
    }

    @Test
    @DisplayName("Elevator moves up and down")
    void testMoveUpDown() {
        elevator.move(1);
        assertEquals(1, elevator.getCurrentFloor());
        elevator.move(-1);
        assertEquals(-1, elevator.getCurrentFloor());
    }

    @Test
    @DisplayName("Status is idle after moving")
    void testStatusAfterMoving() {
        elevator.move(1);
        assertEquals(ElevatorState.IDLE, elevator.getState());
    }

    @Test
    @DisplayName("Status is up while moving up")
    void testStatusWhileMovingUp() throws InterruptedException {
        Thread thread = new Thread(() -> elevator.move(2));
        thread.start();
        Thread.sleep(1000);
        assertEquals(ElevatorState.UP, elevator.getState());
    }

    @Test
    @DisplayName("Status is down while moving down")
    void testStatusWhileMovingDown() throws InterruptedException {
        Thread thread = new Thread(() -> elevator.move(-2));
        thread.start();
        Thread.sleep(1000);
        assertEquals(ElevatorState.DOWN, elevator.getState());
    }




}