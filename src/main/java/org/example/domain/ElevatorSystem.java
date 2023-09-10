package org.example.domain;

import java.util.concurrent.ExecutorService;

public interface ElevatorSystem {
    void addRequest(ElevatorRequest elevatorRequest);
    Elevator[] getElevators();
}
