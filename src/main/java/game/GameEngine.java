package game;

import exceptions.BoardException;

public interface GameEngine {

    void initializeBoard();

    void printBoard() throws BoardException;

    boolean placeMark(int x, int y) throws BoardException;

    char getCoordinateSymbol(int x, int y) throws BoardException;

    boolean isBoardFull();

    boolean checkForWin();

    char getCurrentPlayer();

    void endTurn() throws BoardException;

}
