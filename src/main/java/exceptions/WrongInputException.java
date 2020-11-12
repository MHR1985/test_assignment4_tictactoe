package exceptions;

import java.util.InputMismatchException;

public class WrongInputException extends InputMismatchException {
    public WrongInputException(String message){ super(message);}
}
