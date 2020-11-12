import exceptions.BoardException;
import game.GameEngineImplementation;
import game.GameHandler;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws BoardException {
        GameHandler handler = new GameHandler(new GameEngineImplementation(true),new Scanner(System.in));
        handler.runGame();
    }

}
