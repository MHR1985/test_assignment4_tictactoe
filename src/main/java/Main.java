import exceptions.BoardException;
import game.GameEngineImplementation;
import game.GameHandler;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws BoardException {
        while(true){
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Select gameMode: 1 for PVP - 2 for PVE or 3 to exit the game");
                boolean mode = false;
                int choice = scanner.nextInt();
                if(choice > 0 && choice < 4){
                    if(choice == 3) break;
                    else if (choice == 2){
                        mode = true;
                    }
                    GameHandler handler = new GameHandler(new GameEngineImplementation(mode), scanner);
                    handler.runGame();
                } else {
                    System.out.println("You must select option 1 or 2");
                }
            } catch (InputMismatchException ex){
                System.out.println("Input a number 1 or 2");
            }
        }
    }
}
