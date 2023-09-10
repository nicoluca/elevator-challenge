package org.example.domain.policy;

import org.example.domain.Elevator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class NextIdleElevator extends AbstractElevatorPolicy {
    // TODO Could be a singleton.

    @Override
    public Optional<Elevator> findElevator(Elevator[] elevators) {
        logger.info("Looking for the elevator that will be idle the soonest...");

        Optional<Elevator> optionalElevator = Arrays.stream(elevators)
                .min(Comparator.comparingInt(elevator -> Math.abs(elevator.getDeltaToDestination())));

        if (optionalElevator.isPresent()) {
            logger.info("Found elevator " + optionalElevator.get() + " that will be idle the soonest");
            return optionalElevator;
        } else {
            logger.warning("No elevator found");
            return Optional.empty();
        }
    }
}
