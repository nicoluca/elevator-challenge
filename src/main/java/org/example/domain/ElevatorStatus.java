package org.example.domain;

public enum ElevatorStatus {
    UP,
    DOWN,
    IDLE;

     public boolean directionIsUP() {
        return this.equals(UP);
    }
}