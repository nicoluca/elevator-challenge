package org.example.domain.policy;

import org.example.domain.Elevator;

import java.util.Optional;

@FunctionalInterface
public interface ElevatorPolicy {
    Optional<Elevator> findElevator(Elevator[] elevators);
}
