package org.example.domain;

public interface ElevatorSystem {
    void addRequest(ElevatorRequest elevatorRequest);
    Elevator[] getElevators();

    void printState();
}
