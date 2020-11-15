package game;

import exceptions.BoardException;

import java.util.Arrays;
import java.util.Random;

public class GameEngineImplementation implements GameEngine {

    private char[][] board = null;
    private Character currentPlayer = 'X';
    private final boolean ai;

    public GameEngineImplementation(boolean enableAI) {
        ai = enableAI;
    }

    @Override
    public void initializeBoard() {
        board = new char[3][3];
        for (char[] chars : board) {
            Arrays.fill(chars, '.');
        }

    }

    @Override
    public void printBoard() throws BoardException {
        if (board == null) {
            throw new BoardException("Board is null");
        } else {
            for (char[] chars : board) {
                System.out.println();
                for (int j = 0; j < board.length; j++) {
                    System.out.print(chars[j]);
                }
            }
            System.out.println("\n");
        }
    }

    @Override
    public boolean placeMark(int x, int y) throws BoardException {
        checkBoard();
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            throw new BoardException("Coordinates too low or high");
        }
        if (board[x - 1][y - 1] == 'X' || board[x - 1][y - 1] == 'O') {
            return false;
        }
        board[x - 1][y - 1] = currentPlayer;
        return true;

    }


    @Override
    public char getCoordinateSymbol(int x, int y) throws BoardException {
        checkBoard();
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            throw new BoardException("Coordinates not too low or high");
        }
        return board[x - 1][y - 1];

    }

    @Override
    public char getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void endTurn() throws BoardException {
        switchPlayer();

        if (ai && currentPlayer.equals('O')) {
            aiMove();
        }
    }

    private void switchPlayer() {
        if (currentPlayer.equals('X')) {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }

    private void checkBoard() throws BoardException {
        if (board == null)
            throw new BoardException("Board is null");
       }

    @Override
    public boolean isBoardFull() {
        for (char[] chars : board) {
            for (char aChar : chars) {
                if (aChar == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean checkForWin() {
        return checkDiagonalsForWin() || checkRowsForWin() || checkColumnsForWin();
    }


    private boolean checkColumnsForWin() {
        if (board[0][0] == 'X' && board[1][0] == 'X' && board[2][0] == 'X') {
            return true;
        } else if (board[0][0] == 'O' && board[1][0] == 'O' && board[2][0] == 'O') {
            return true;
        } else if (board[0][1] == 'X' && board[1][1] == 'X' && board[2][1] == 'X') {
            return true;
        } else if (board[0][1] == 'O' && board[1][1] == 'O' && board[2][1] == 'O') {
            return true;
        } else if (board[0][2] == 'O' && board[1][2] == 'O' && board[2][2] == 'O') {
            return true;
        } else return board[0][2] == 'X' && board[1][2] == 'X' && board[2][2] == 'X';
    }

    private boolean checkRowsForWin() {
        if (board[0][0] == 'X' && board[0][1] == 'X' && board[0][2] == 'X') {
            return true;
        } else if (board[0][0] == 'O' && board[0][1] == 'O' && board[0][2] == 'O') {
            return true;
        } else if (board[1][0] == 'X' && board[1][1] == 'X' && board[1][2] == 'X') {
            return true;
        } else if (board[1][0] == 'O' && board[1][1] == 'O' && board[1][2] == 'O') {
            return true;
        } else if (board[2][0] == 'O' && board[2][1] == 'O' && board[2][2] == 'O') {
            return true;
        } else return board[2][0] == 'X' && board[2][1] == 'X' && board[2][2] == 'X';
    }

    private boolean checkDiagonalsForWin() {
        if (board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') {
            return true;
        } else if (board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') {
            return true;
        } else if (board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O') {
            return true;
        } else return board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X';
    }

    private void aiMove() throws BoardException {
        Random rand = new Random();
        int min = 1;
        int max = 3;
        int x = rand.nextInt((max - min) + 1) + min;
        int y = rand.nextInt((max - min) + 1) + min;
        while (!placeMark(x, y) && !checkForWin() && !isBoardFull()) {
            x = rand.nextInt((max - min) + 1) + min;
            y = rand.nextInt((max - min) + 1) + min;
        }
        if(!checkForWin()){
            endTurn();
        }
    }
}
