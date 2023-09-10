package org.example.domain;

public class ElevatorSystemState {
    private final ElevatorSystem elevatorSystem;

    public ElevatorSystemState(ElevatorSystem elevatorSystem) {
        this.elevatorSystem = elevatorSystem;
    }

    public int getElevatorCount() {
        return elevatorSystem.getElevators().length;
    }

    public int[] getCurrentFloors() {
        int[] currentFloors = new int[getElevatorCount()];
        for (int i = 0; i < getElevatorCount(); i++)
            currentFloors[i] = elevatorSystem.getElevators()[i].getCurrentFloor();

        return currentFloors;
    }

    public ElevatorState[] getStates() {
        ElevatorState[] states = new ElevatorState[getElevatorCount()];
        for (int i = 0; i < getElevatorCount(); i++)
            states[i] = elevatorSystem.getElevators()[i].getState();

        return states;
    }
}
