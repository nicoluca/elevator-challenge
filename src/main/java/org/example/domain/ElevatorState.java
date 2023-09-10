package org.example.domain;

public enum ElevatorState {
    UP,
    DOWN,
    IDLE;

     public boolean directionIsUP() {
        return this.equals(UP);
    }
}