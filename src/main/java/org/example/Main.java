package org.example;

import org.example.config.Config;
import org.example.domain.ElevatorRequest;
import org.example.domain.ElevatorState;
import org.example.domain.ElevatorSystem;
import org.example.domain.ElevatorSystemImpl;
import org.example.domain.policy.ElevatorPolicy;
import org.example.domain.policy.IdleElevator;
import org.example.domain.policy.NextIdleElevator;

import java.util.Random;
import java.util.logging.Logger;

public class Main {
    Logger logger = Logger.getLogger(getClass().getName());
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello, elevator world!");
        System.out.println("Running some random requests...\n");

        ElevatorSystem elevatorSystem = new ElevatorSystemImpl(new ElevatorPolicy[]{new IdleElevator(), new NextIdleElevator()});
        Random random = new Random();

        while (true) {
            elevatorSystem.printState();

            // Add a random request going down.
            int originFloor = random.nextInt(Config.MAXIMUM_FLOOR);
            elevatorSystem.addRequest(new ElevatorRequest(originFloor, 0, ElevatorState.DOWN));
            // Add a random request going up.
            int destinationFloor = random.nextInt(Config.MAXIMUM_FLOOR) + 1;
            elevatorSystem.addRequest(new ElevatorRequest(0, destinationFloor, ElevatorState.UP));

            Thread.sleep(10000); // Configure this to change the speed of the simulation.
        }
    }
}