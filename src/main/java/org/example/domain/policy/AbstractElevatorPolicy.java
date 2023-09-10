package org.example.domain.policy;

import org.example.domain.Elevator;

import java.util.Optional;
import java.util.logging.Logger;

abstract class AbstractElevatorPolicy implements ElevatorPolicy {
    protected Logger logger = Logger.getLogger(AbstractElevatorPolicy.class.getName());
}
