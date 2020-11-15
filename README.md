# Test: Tic-Tac-Toe Assignment 4

## Description
This is our implementation of Assignment 4 in Test class. We chose to implement a tic-tac-toe game and fully covered with unittests.

### How to run
You run our game by running the Main class in the project. Simple as that.

### Code Coverage
Open /coverage/index.html in browser to see code coverage.

### Mutation Coverage
Open /mutationCoverage/202011151247/index.html in browser to see mutation coverage.
You can also choose to generate the mutation report yourself. Maven needs to be installed globally. To generate the report run this command in the terminal:
* mvn clean test org.pitest:pitest-maven:mutationCoverage.

### Static Analysis
We have performed a static analysis with QAPlug, to inspect and refactor the code;

## Mockito Powerups

* How do you verify that a mock was called?
    * Verify is the method you use to verify a mock. Here is an example of how it is used:
     ```
      verify(mockObject).someMethod();
     ```
* How do you verify that a mock was NOT called?
    * This is done almost the same way. To do this you add never() as an extra parameter to the verify method.
    ```
    verify(mockObject, never()).someMethod();
    ```

* How do you specify how many times a mock should have been called?
    * When you want to specify how many times a mock should be called, you add the times parameter to the verify method.
    ```
    verify(mockObject, times(n)).someMethodOfMockObject(args);
    ```
* How do you verify that a mock was called with specific arguments?
    * To verify a mock was called with specific arguments, you chain the verify method with the method 
    ```
    verify(storageMock)
                    .createBooking(
                            argThat(x -> x.getCustomerId() == customerId &&
                                    x.getEmployeeId() == employeeId &&
                                    x.getDate().equals(date) &&
                                    x.getStart().equals(start) &&
                                    x.getEnd().equals(end)
                            ));
     ```
 * How do you use a predicate to verify the properties of the arguments given to a call to the mock?
    * We have not been able to determine how to use predicate with Mockito.

