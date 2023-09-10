package org.example.domain;

import org.example.config.Config;
import org.example.domain.policy.ElevatorPolicy;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class ElevatorSystemImpl implements ElevatorSystem {

    private final Logger logger = Logger.getLogger(getClass().getName());

    private final Elevator[] elevators = new Elevator[Config.NUMBER_OF_ELEVATORS];

    // Using a thread pool to simulate multiple elevators moving at the same time.
    // This gives us the ability to just submit a task to the thread pool, and it will be executed when a thread is available.
    // I.e. once an elevator is idle, it will be available to execute the next task, and the respective thread will also be available.
    private final ExecutorService executorService = Executors.newFixedThreadPool(Config.NUMBER_OF_ELEVATORS);

    // The idea here is to have a chain of responsibility of policies, that can be flexibly configured/extended.
    // For demo purposes, only two simple policies are implemented. Extension could be examining the destination floor of running elevators etc.
    private final ElevatorPolicy[] nextElevatorPolicies;

    public ElevatorSystemImpl(ElevatorPolicy... nextElevatorPolicies) {
        for (int i = 0; i < elevators.length; i++)
            elevators[i] = new ElevatorImpl();

        this.nextElevatorPolicies = nextElevatorPolicies;
    }

    @Override
    public void addRequest(ElevatorRequest elevatorRequest) {
        logger.info("Adding request " + elevatorRequest);

        for (ElevatorPolicy nextElevatorPolicy : nextElevatorPolicies) {
            Optional<Elevator> elevator = nextElevatorPolicy.findElevator(elevators);
            if (elevator.isPresent()) {
                assignRequestToElevator(elevatorRequest, elevator.get());
                return;
            }
        }

    }

    @Override
    public void printState() {
        System.out.println("\n##### Elevator system state: #####");
        for (int i = 0; i < elevators.length; i++)
            System.out.println("Elevator " + (i+1) + " is at floor " + elevators[i].getCurrentFloor()
                    + " and movement state is " + elevators[i].getState());
    }

    private void assignRequestToElevator(ElevatorRequest elevatorRequest, Elevator elevator) {
        executorService.submit(
                () -> {
                    elevator.move(elevatorRequest.getOriginFloor());
                    elevator.move(elevatorRequest.getDestinationFloor());
                }
        );
    }

}
