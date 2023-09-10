package org.example.domain;

public class ElevatorRide implements Runnable {
    private final Elevator elevator;
    private final ElevatorRequest elevatorRequest;

    public ElevatorRide(Elevator elevator, ElevatorRequest elevatorRequest) {
        this.elevator = elevator;
        this.elevatorRequest = elevatorRequest;
    }

    @Override
    public void run() {
        elevator.move(elevatorRequest.getDestinationFloor());
    }
}
