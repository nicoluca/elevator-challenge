package org.example.domain.policy;

import lombok.NoArgsConstructor;
import org.example.domain.Elevator;
import org.example.domain.ElevatorState;

import java.util.Arrays;
import java.util.Optional;

@NoArgsConstructor
public class IdleElevator extends AbstractElevatorPolicy {
    // Could be a singleton

    @Override
    public Optional<Elevator> findElevator(Elevator[] elevators) {
        logger.info("Looking for an idle elevator...");
        Optional<Elevator> optionalElevator = Arrays.stream(elevators)
                .filter(elevator -> elevator.getState() == ElevatorState.IDLE)
                .findFirst();

        if (optionalElevator.isPresent()) {
            logger.info("Found idle elevator " + optionalElevator.get());
            return optionalElevator;
        } else {
            logger.warning("No idle elevator found");
            return Optional.empty();
        }
    }
}
