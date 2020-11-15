package game;

import exceptions.BoardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.io.ByteArrayInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GameEngineTest {

    private GameEngineImplementation engine;

    @BeforeEach
    public void setup() {
        engine = new GameEngineImplementation(false);
        engine.initializeBoard();
    }

    @Test
    public void TestGameEngineNotNull(){
        //arrange
        //act
        //assert
        assertNotNull(engine);
    }

    @Test
    public void TestPrintBoardThrowsExceptionWhenBoardHasNotBeenInitialized(){
        //arrange
        GameEngine engine = new GameEngineImplementation(false);
        //act
        //assert
        Exception ex = assertThrows(BoardException.class,engine::printBoard);
        assertNotNull(ex);
    }

    @Test
    public void TestGetCoordinateSymbolThrowsExceptionWhenInvalidCoordinates(){
        //arrange
        GameEngine engine = new GameEngineImplementation(false);
        //act
        //assert
        Exception ex = assertThrows(BoardException.class,()-> engine.getCoordinateSymbol(0,0));
        assertNotNull(ex);
    }

    @Test
    public void TestBoardHasBeenInitialized() {
        //arrange
        //act
        //assert
        try{
            engine.printBoard();
            assertTrue(true);
        }catch(BoardException ex){
            fail();
        }
    }


    @Test
    public void TestGetCoordinateSymbol() throws BoardException {
        //arrange
        //act
        char symbol = engine.getCoordinateSymbol(1,1);
        //assert
        assertEquals(symbol, '.');


    }

    @Test
    public void TestMarkHasBeenPlaced() throws BoardException {
        //arrange
        //act
        engine.placeMark(1,1);
        char symbol = engine.getCoordinateSymbol(1,1);
        //assert
        assertTrue(symbol=='X'||symbol=='O');
    }

    @Test
    public void TestPlaceMarkMustThrowExceptionWhenInvalidCoordinates() {
        //arrange
        GameEngine engine = new GameEngineImplementation(false);
        //act
        //assert
        Exception ex = assertThrows(BoardException.class,()->{
            engine.placeMark(0,0);
            engine.placeMark(4,4);
        });
        assertNotNull(ex);
    }

    @Test
    public void TestGetCoordinateSymbolMustThrowExceptionWhenInvalidCoordinates() {
        //arrange
        GameEngine engine = new GameEngineImplementation(false);
        //act
        //assert
        Exception ex = assertThrows(BoardException.class,()->{
            engine.getCoordinateSymbol(0,0);
            engine.getCoordinateSymbol(4,4);
        });
        assertNotNull(ex);
    }

    @Test
    public void TestIsBoardFull() throws BoardException {
        //arrange
        //act
        engine.placeMark(1,1);
        engine.endTurn();
        engine.placeMark(1,2);
        engine.endTurn();
        engine.placeMark(1,3);
        engine.endTurn();
        engine.placeMark(2,1);
        engine.endTurn();
        engine.placeMark(2,2);
        engine.endTurn();
        engine.placeMark(2,3);
        engine.endTurn();
        engine.placeMark(3,1);
        engine.endTurn();
        engine.placeMark(3,2);
        engine.endTurn();
        engine.placeMark(3,3);
        engine.endTurn();
        //assert
        engine.printBoard();
        assertTrue(engine.isBoardFull());
    }

    @Test
    public void TestCannotPlaceMarkerOnAlreadyMarkedField() throws BoardException {
        //arrange
        //act
        engine.placeMark(1,1);
        //assert
        assertFalse(engine.placeMark(1,1));
    }

    @Test
    public void TestIsBoardNotFull() {
        //arrange
        //act
        //assert
        assertFalse(engine.isBoardFull());
    }

    @Test
    public void TestForDiagonalWinX1() throws BoardException {
        //arrange
        //act
        engine.placeMark(1,1);
        engine.endTurn();
        engine.placeMark(1,2);
        engine.endTurn();
        engine.placeMark(1,3);
        engine.endTurn();
        engine.placeMark(2,1);
        engine.endTurn();
        engine.placeMark(2,2);
        engine.endTurn();
        engine.placeMark(2,3);
        engine.endTurn();
        engine.placeMark(3,1);
        engine.endTurn();
        engine.placeMark(3,2);
        engine.endTurn();
        engine.placeMark(3,3);
        engine.endTurn();
        //assert
        engine.printBoard();
        assertTrue(engine.checkForWin());
    }

    @Test
    public void TestForDiagonalWinO1() throws BoardException {
        //arrange
        //act
        engine.endTurn();
        engine.placeMark(1,1);
        engine.placeMark(2,2);
        engine.placeMark(3,3);
        //assert
        engine.printBoard();
        assertTrue(engine.checkForWin());
    }

    @Test
    public void TestForDiagonalWinX2() throws BoardException {
        //arrange
        //act
        engine.placeMark(1,3);
        engine.placeMark(2,2);
        engine.placeMark(3,1);
        //assert
        engine.printBoard();
        assertTrue(engine.checkForWin());
    }
    @Test
    public void TestForDiagonalWinO2() throws BoardException {
        //arrange
        //act
        engine.endTurn();
        engine.placeMark(1,3);
        engine.placeMark(2,2);
        engine.placeMark(3,1);
        //assert
        engine.printBoard();
        assertTrue(engine.checkForWin());
    }


    @Test
    public void TestForRowWinX1() throws BoardException {
        //arrange
        //act
        engine.placeMark(1,1);
        engine.placeMark(2,1);
        engine.placeMark(3,1);
        //assert
        engine.printBoard();
        assertTrue(engine.checkForWin());
    }

    @Test
    public void TestForRowWinX2() throws BoardException {
        //arrange
        //act
        engine.placeMark(1,2);
        engine.placeMark(2,2);
        engine.placeMark(3,2);
        //assert
        engine.printBoard();
        assertTrue(engine.checkForWin());
    }

    @Test
    public void TestForRowWinX3() throws BoardException {
        //arrange
        //act
        engine.placeMark(1,3);
        engine.placeMark(2,3);
        engine.placeMark(3,3);
        //assert
        engine.printBoard();
        assertTrue(engine.checkForWin());
    }

    @Test
    public void TestForRowWinO1() throws BoardException {
        //arrange
        //act
        engine.endTurn();
        engine.placeMark(1,1);
        engine.placeMark(2,1);
        engine.placeMark(3,1);
        //assert
        engine.printBoard();
        assertTrue(engine.checkForWin());
    }

    @Test
    public void TestForRowWinO2() throws BoardException {
        //arrange
        //act
        engine.endTurn();
        engine.placeMark(1,2);
        engine.placeMark(2,2);
        engine.placeMark(3,2);
        //assert
        engine.printBoard();
        assertTrue(engine.checkForWin());
    }

    @Test
    public void TestForRowWinO3() throws BoardException {
        //arrange
        //act
        engine.endTurn();
        engine.placeMark(1,3);
        engine.placeMark(2,3);
        engine.placeMark(3,3);
        //assert
        engine.printBoard();
        assertTrue(engine.checkForWin());
    }

    @Test
    public void TestForColumnWinX1() throws BoardException {
        //arrange
        //act
        engine.placeMark(1,1);
        engine.placeMark(1,2);
        engine.placeMark(1,3);
        //assert
        engine.printBoard();
        assertTrue(engine.checkForWin());
    }

    @Test
    public void TestForColumnWinX2() throws BoardException {
        //arrange
        //act
        engine.placeMark(2,1);
        engine.placeMark(2,2);
        engine.placeMark(2,3);
        //assert
        engine.printBoard();
        assertTrue(engine.checkForWin());
    }

    @Test
    public void TestForColumnWinX3() throws BoardException {
        //arrange
        //act
        engine.placeMark(3,1);
        engine.placeMark(3,2);
        engine.placeMark(3,3);
        //assert
        engine.printBoard();
        assertTrue(engine.checkForWin());
    }

    @Test
    public void TestForColumnWinO1() throws BoardException {
        //arrange
        //act
        engine.endTurn();
        engine.placeMark(1,1);
        engine.placeMark(1,2);
        engine.placeMark(1,3);
        //assert
        engine.printBoard();
        assertTrue(engine.checkForWin());
    }

    @Test
    public void TestForColumnWinO2() throws BoardException {
        //arrange
        //act
        engine.endTurn();
        engine.placeMark(2,1);
        engine.placeMark(2,2);
        engine.placeMark(2,3);
        //assert
        engine.printBoard();
        assertTrue(engine.checkForWin());
    }

    @Test
    public void TestForColumnWinO3() throws BoardException {
        //arrange
        //act
        engine.endTurn();
        engine.placeMark(3,1);
        engine.placeMark(3,2);
        engine.placeMark(3,3);
        //assert
        engine.printBoard();
        assertTrue(engine.checkForWin());
    }

    @Test
    public void TestEndTurn() throws BoardException {
        //arrange
        //act
        char c = engine.getCurrentPlayer();
        engine.endTurn();

        assertNotEquals(c, engine.getCurrentPlayer());
    }

    @Test
    public void mustThrowInputMismatchException(){

        ByteArrayInputStream in = new ByteArrayInputStream("-!".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        GameHandler handler = new GameHandler(new GameEngineImplementation(false), scanner);
        Exception ex = assertThrows(InputMismatchException.class,()-> handler.getInput(scanner,"x"));
        assertNotNull(ex);
    }

    @Test
    public void aiMustPlaceMarkersTest() throws BoardException {
        GameEngine engine = new GameEngineImplementation(true);
        engine.initializeBoard();

        for (int i = 0; i < 9; i++) {
            engine.endTurn();
            engine.printBoard();
            if (engine.checkForWin()){
                assertTrue(engine.checkForWin());
                break;
            }
        }
    }
}
