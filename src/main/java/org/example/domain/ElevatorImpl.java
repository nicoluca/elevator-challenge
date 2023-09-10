package org.example.domain;

import lombok.NoArgsConstructor;
import org.example.config.Config;

import java.util.logging.Logger;

@NoArgsConstructor
public class ElevatorImpl implements Elevator {

    private final Logger logger = Logger.getLogger(getClass().getName());
    private int currentFloor = 0;
    private ElevatorState state = ElevatorState.IDLE;

    private int currentDelta = 0;

    @Override
    public void move(int toFloor) {
        this.state = toFloor > this.currentFloor ? ElevatorState.UP : ElevatorState.DOWN;
        this.currentDelta = toFloor - this.currentFloor;
        startMoving(toFloor);
    }

    @Override
    public int getCurrentFloor() {
        return this.currentFloor;
    }

    public synchronized ElevatorState getState() {
        return this.state;
    }

    @Override
    public synchronized int getCurrentDelta() {
        return this.currentDelta;
    }

    private void startMoving(int toFloor) {
        logger.info("Elevator in thread " + Thread.currentThread().getName()
                + " is starting to move from floor " + this.currentFloor + " to floor " + toFloor);

        try {
            moveElevator(toFloor);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            this.state = ElevatorState.IDLE;
            logger.info("Elevator in thread " + Thread.currentThread().getName() + " is now idle");
        }
    }

    private void moveElevator(int toFloor) throws InterruptedException {
        while (this.currentFloor != toFloor) {
            Thread.sleep(Config.TIME_BETWEEN_FLOORS_IN_MS); // Simulate moving
            this.currentFloor = this.currentFloor < toFloor ? this.currentFloor + 1 : this.currentFloor - 1;
            this.currentDelta = toFloor - this.currentFloor;
            logger.info("Elevator in thread " + Thread.currentThread().getName() + " is now at floor " + this.currentFloor);
        }
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "currentFloor=" + currentFloor +
                ", state=" + state +
                '}';
    }
}
