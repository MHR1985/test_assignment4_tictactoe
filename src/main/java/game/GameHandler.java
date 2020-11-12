package game;

import exceptions.BoardException;
import exceptions.WrongInputException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameHandler {

    private GameEngine engine;
    private Scanner scan;

    public GameHandler(GameEngine engine, Scanner scan) {
        this.engine = engine;
        this.scan = scan;
    }

    public void runGame() throws BoardException {
        engine.initializeBoard();
        while (!engine.checkForWin()) {
            engine.printBoard();
            if (engine.isBoardFull()) break;
            System.out.println("It is player: " + engine.getCurrentPlayer());
            Integer x = null;
            Integer y = null;

            try{
                x = getInput(scan, "x");
                y = getInput(scan, "y");
            }catch(WrongInputException ex) {
                System.out.println(ex.getMessage());
            }

            if(y != null && x != null){
                if (engine.placeMark(x, y)) {
                    engine.endTurn();
                }
            }
        }
        System.out.println("\nGAME OVER!");
        if (engine.checkForWin()) {
            if (engine.getCurrentPlayer() == 'X') {
                System.out.println("PLAYER O WON!");
            } else {
                System.out.println("PLAYER X WON!");
            }
            engine.printBoard();
        } else if (engine.isBoardFull()) {
            System.out.println("IT'S A DRAW");
        }
    }

    protected int getInput(Scanner scanner, String axis){
        try{
            System.out.println("Enter " + axis + " coordinate");
            return scanner.nextInt();
        } catch (InputMismatchException ex) { throw new WrongInputException("You must type a valid number (1-3)"); }
    }
}
