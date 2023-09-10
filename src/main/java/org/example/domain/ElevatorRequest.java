package org.example.domain;

import lombok.Getter;
import org.example.config.Config;

@Getter
public class ElevatorRequest {

    private final int originFloor;
    private final int destinationFloor;
    private final ElevatorState direction;

    public ElevatorRequest(int originFloor, int destinationFloor, ElevatorState direction) {
        /*
        TODO Not quite sure why you would need direction in here... but sticking to the requirements for now.
         We'll just use it for argument validity handling. In a real scenario, i'd try to clarify the requirements...
         */
        checkRequestValidity(originFloor, destinationFloor, direction);

        this.originFloor = originFloor;
        this.destinationFloor = destinationFloor;
        this.direction = direction;
    }

    private void checkRequestValidity(int originFloor, int destinationFloor, ElevatorState direction) {
        if (isInvalidFloor(originFloor))
            throwFloorNumberException(originFloor);
        if (isInvalidFloor(destinationFloor))
            throwFloorNumberException(destinationFloor);

        if (isInvalidFloorCombination(originFloor, destinationFloor, direction))
            throw new IllegalArgumentException("Invalid combination of argumentes: \n" +
                    "Origin floor: " + originFloor + ", destination floor: " + destinationFloor + ", direction: " + direction + ".");
    }

    private void throwFloorNumberException(int originFloor) {
        throw new IllegalArgumentException("Floor number not in valid range." +
                "Floor requested: " + originFloor +
                " (minimum: " + Config.MINIMUM_FLOOR + "maximum: " + Config.MINIMUM_FLOOR + ").");
    }

    private boolean isInvalidFloor(int floor) {
        return floor < Config.MINIMUM_FLOOR || floor > Config.MAXIMUM_FLOOR;
    }

    private boolean isInvalidFloorCombination(int originFloor, int destinationFloor, ElevatorState direction) {
        return originFloor == destinationFloor
                || originFloor < destinationFloor && direction.equals(ElevatorState.DOWN)
                || originFloor > destinationFloor && direction.equals(ElevatorState.UP);
    }

    @Override
    public String toString() {
        return "ElevatorRequest{" +
                "originFloor=" + originFloor +
                ", destinationFloor=" + destinationFloor +
                ", direction=" + direction +
                '}';
    }

}
