package game;

import exceptions.BoardException;
import game.GameEngine;

import java.util.Arrays;
import java.util.Random;

public class GameEngineImplementation implements GameEngine {

    private char[][] board = null;
    private Character currentPlayer = 'X';
    private boolean ai;

    public GameEngineImplementation(boolean enableAI) {
        ai = enableAI;
    }

    @Override
    public void initializeBoard() {
        board = new char[3][3];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], '.');
        }

    }

    @Override
    public void printBoard() throws BoardException {
        if (board == null) {
            throw new BoardException("Board is null");
        } else {
            for (int i = 0; i < board.length; i++) {
                System.out.println();
                for (int j = 0; j < board.length; j++) {
                    System.out.print(board[i][j]);
                }
            }
        }
    }

    @Override
    public boolean placeMark(int x, int y) throws BoardException {
        getBoard();
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
        getBoard();
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

    private char[][] getBoard() throws BoardException {
        if (board != null) {
            return board;
        } else throw new BoardException("Board is null");
    }

    @Override
    public boolean isBoardFull() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean checkForWin() {
        if (checkDiagonalsForWin() || checkRowsForWin() || checkColumnsForWin()) {
            return true;
        }
        return false;
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
        } else if (board[0][2] == 'X' && board[1][2] == 'X' && board[2][2] == 'X') {
            return true;
        }
        return false;
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
        } else if (board[2][0] == 'X' && board[2][1] == 'X' && board[2][2] == 'X') {
            return true;
        }
        return false;
    }

    private boolean checkDiagonalsForWin() {
        if (board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') {
            return true;
        } else if (board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') {
            return true;
        } else if (board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O') {
            return true;
        } else if (board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X') {
            return true;
        }
        return false;
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
