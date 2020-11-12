package game;

import exceptions.BoardException;
import game.GameEngine;

import java.util.Scanner;

public class GameHandler {

    private GameEngine engine;
    private Scanner scan;

    public GameHandler(GameEngine engine,Scanner scan){
        this.engine = engine;
        this.scan = scan;
    }

    public void runGame() throws BoardException {
        engine.initializeBoard();
        while(!engine.checkForWin() || !engine.isBoardFull()){
            engine.printBoard();
            System.out.println("");
            System.out.println("It is player: " + engine.getCurrentPlayer());
            System.out.println("Enter x coordinate");
            int x = scan.nextInt();
            System.out.println("Enter y coordinate");
            int y = scan.nextInt();
            if(engine.placeMark(x,y)){
                engine.endTurn();
            };
            System.out.println("");

        }
        if(engine.checkForWin()){
            if(engine.getCurrentPlayer()=='X'){
                System.out.println("PLAYER O WON!");
            }else{
                System.out.println("PLAYER X WON!");
            }
            engine.printBoard();
        }else if(engine.isBoardFull()){
            System.out.println("IT'S A DRAW");
        }

    }
}
