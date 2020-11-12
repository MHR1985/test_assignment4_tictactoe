#Assignment 4

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
     ```"# Assignment4" 