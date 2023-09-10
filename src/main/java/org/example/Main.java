package org.example;

import org.example.config.Config;
import org.example.domain.ElevatorRequest;
import org.example.domain.ElevatorState;
import org.example.domain.ElevatorSystem;
import org.example.domain.ElevatorSystemImpl;

import java.util.Random;
import java.util.logging.Logger;

public class Main {
    Logger logger = Logger.getLogger(getClass().getName());
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello, elevator world!");
        System.out.println("Running some random requests...\n");

        ElevatorSystem elevatorSystem = new ElevatorSystemImpl();

        while (true) {
            elevatorSystem.printState();

            int originFloor = new Random().nextInt(Config.MAXIMUM_FLOOR);
            elevatorSystem.addRequest(new ElevatorRequest(originFloor, 0, ElevatorState.DOWN));
            int destinationFloor = new Random().nextInt(Config.MAXIMUM_FLOOR);
            elevatorSystem.addRequest(new ElevatorRequest(0, destinationFloor, ElevatorState.UP));

            Thread.sleep(10000);

        }
    }
}