package org.example.domain.policy;

import java.util.logging.Logger;

abstract class AbstractElevatorPolicy implements ElevatorPolicy {
    protected Logger logger = Logger.getLogger(AbstractElevatorPolicy.class.getName());
}
