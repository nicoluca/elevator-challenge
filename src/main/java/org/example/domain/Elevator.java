package org.example.domain;

public interface Elevator {
    void move(int toFloor);
    int getCurrentFloor();
    ElevatorStatus getStatus();
}
