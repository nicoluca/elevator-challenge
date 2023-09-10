package org.example.domain;

import lombok.Getter;
import org.example.config.Config;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class ElevatorSystemImpl implements ElevatorSystem {

    private final Logger logger = Logger.getLogger(getClass().getName());

    private final Elevator[] elevators = new Elevator[Config.NUMBER_OF_ELEVATORS];
    private final ExecutorService executorService = Executors.newFixedThreadPool(Config.NUMBER_OF_ELEVATORS);

    public ElevatorSystemImpl() {
        for (int i = 0; i < elevators.length; i++)
            elevators[i] = new ElevatorImpl();
    }

    @Override
    public void addRequest(ElevatorRequest elevatorRequest) {
        logger.info("Adding request " + elevatorRequest);

        // Check if there is an idle elevator
        Optional<Elevator> idleElevator = this.getIdleElevator();

        // TODO Abstract policies from here, not only the idle elevator policy

        // If there is an idle elevator, assign the request to it (move it to the origin floor, then to the destination floor)
        if (idleElevator.isPresent()) {
            logger.info("Found idle elevator " + idleElevator.get());
            assignRequestToElevator(elevatorRequest, idleElevator.get());
            return;
        } else {
            logger.info("No idle elevator found");
        }

        // If there is no idle elevator, assign the request to the elevator that will be idle the soonest
        Elevator elevator = getNextIdleElevator();
        assignRequestToElevator(elevatorRequest, elevator);

    }

    @Override
    public Elevator[] getElevators() {
        return this.elevators;
    }

    private void assignRequestToElevator(ElevatorRequest elevatorRequest, Elevator elevator) {
        executorService.submit(
                () -> {
                    elevator.move(elevatorRequest.getOriginFloor());
                    elevator.move(elevatorRequest.getDestinationFloor());
                }
        );
    }

    private Optional<Elevator> getIdleElevator() {
        return Arrays.stream(elevators).findAny().filter(
                elevator -> elevator.getState() == ElevatorState.IDLE);
    }

    private Elevator getNextIdleElevator() {
        return Arrays.stream(elevators).min(
                Comparator.comparingInt(Elevator::getCurrentDelta)
        ).get();
    }
}
