package org.example.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ElevatorRequestTest {

    @Test
    @DisplayName("Should pass when request is valid")
    void shouldPassWhenRequestIsValid() {
        // given
        int originFloor = 0;
        int destinationFloor = 2;
        ElevatorState direction = ElevatorState.UP;

        // when
        ElevatorRequest elevatorRequest = new ElevatorRequest(originFloor, destinationFloor, direction);

        // then
        assertEquals(originFloor, elevatorRequest.getOriginFloor());
        assertEquals(destinationFloor, elevatorRequest.getDestinationFloor());
        assertEquals(direction, elevatorRequest.getDirection());
    }

    @Test
    @DisplayName("Should throw exception when origin floor is invalid")
    void shouldThrowExceptionWhenOriginFloorIsInvalid() {
        int originFloor = -1;
        int destinationFloor = 2;
        ElevatorState direction = ElevatorState.UP;

        assertThrows(IllegalArgumentException.class, () -> new ElevatorRequest(originFloor, destinationFloor, direction));
    }

    @Test
    @DisplayName("Should throw exception when destination floor is invalid")
    void shouldThrowExceptionWhenDestinationFloorIsInvalid() {
        int originFloor = 1;
        int destinationFloor = 100;
        ElevatorState direction = ElevatorState.UP;

        assertThrows(IllegalArgumentException.class, () -> new ElevatorRequest(originFloor, destinationFloor, direction));
    }

    @Test
    @DisplayName("Should throw exception when origin and destination floor are the same")
    void shouldThrowExceptionWhenOriginAndDestinationFloorAreTheSame() {
        int originFloor = 1;
        int destinationFloor = 1;
        ElevatorState direction = ElevatorState.UP;

        assertThrows(IllegalArgumentException.class, () -> new ElevatorRequest(originFloor, destinationFloor, direction));
    }

    @Test
    @DisplayName("Should throw exception when combination of origin floor, destination floor and direction is invalid")
    void shouldThrowExceptionWhenCombinationOfOriginFloorDestinationFloorAndDirectionIsInvalid() {
        int originFloor = 1;
        int destinationFloor = 2;
        ElevatorState direction = ElevatorState.DOWN;

        assertThrows(IllegalArgumentException.class, () -> new ElevatorRequest(originFloor, destinationFloor, direction));
    }


}