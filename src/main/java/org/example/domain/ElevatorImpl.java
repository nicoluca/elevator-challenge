package org.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.logging.Logger;

@NoArgsConstructor
@Getter
public class ElevatorImpl implements Elevator {

    private final Logger logger = Logger.getLogger(getClass().getName());
    private int currentFloor = 0;
    private ElevatorStatus status = ElevatorStatus.IDLE;

    @Override
    public void move(int toFloor) {
        this.status = toFloor > this.currentFloor ? ElevatorStatus.UP : ElevatorStatus.DOWN;
        startMoving(toFloor);

        // TODO Elevators first need to start at current floor, then go to origin, then destination, but this should be implemented in controller
        // TODO Elevators should probably return to floor 0 if idle?
    }

    @Override
    public int getCurrentFloor() {
        return this.currentFloor;
    }

    private void startMoving(int toFloor) {
        logger.info("Elevator is starting to move from floor " + this.currentFloor + " to floor " + toFloor);

        try {
            moveElevator(toFloor);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            this.status = ElevatorStatus.IDLE;
            logger.info("Elevator in thread " + Thread.currentThread().getName() + " is now idle");
        }
    }

    private void moveElevator(int toFloor) throws InterruptedException {
        while (this.currentFloor != toFloor) {
            Thread.sleep(1000);
            this.currentFloor = this.currentFloor < toFloor ? this.currentFloor + 1 : this.currentFloor - 1;
            logger.info("Elevator is now at floor " + this.currentFloor);
        }
    }
}
