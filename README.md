# DC Tower Elevator Challenge

## Requirements
- Design and implement an elevator control system
- Elevators move to and from ground floor to any floor
- Only the 'pool' of elevators can be called, not a specific one
- Requests are queued and serviced in order of arrival
- A request comes in the form of (current floor, destination floor, direction)

## Implementation Notes
- Configuration (number of elevators etc.) is done via [Config.java](src/main/java/org/example/config/Config.java).
- Policies for elevator selection are implemented in [the policy folder](src/main/java/org/example/domain/policy). The implement and inherit from respective interfaces and abstract classes, and are easily extensible. The order of the policies in the list determines the order in which they are applied.
- Elevator, ElevatorRequest and ElevatorSystem are distinct classes (partly) implementing respective interfaces.
- Tests are implemented in [the test folder](src/test/java/org/example/domain). They are not exhaustive, but mostly for demonstration purposes.
- The [Main class](src/main/java/org/example/Main.java) runs a simple, endless simulation of the elevator system.

## Improvements/Todos
The following improvements could be made, as the time and scope of this project did not allow for them:
- Test coverage, incl edge cases.
- More sophisticated policies, e.g. based on distance to destination, or based on the number of requests in the queue.
- The alternative logic for the elevator movement (e.g. not only 3 -> 14, 14 -> 0, etc., but also 3 -> 7 -> 14, 14 -> 7 -> 0, etc.) could be implemented by interrupting the current movement (i.e. the thread in which the elevator is running), and restarting it with the new destination. This would require some refactoring of the elevator class, and the implementation of a new policy to find the next elevator to be called.