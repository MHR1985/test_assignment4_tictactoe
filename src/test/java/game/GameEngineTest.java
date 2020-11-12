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
        Exception ex = assertThrows(BoardException.class,()->{
           engine.printBoard();
        });
        assertNotNull(ex);
    }

    @Test
    public void TestBoardHasBeenInitialized() throws BoardException {
        //arrange
        //act
        //assert
        try{
            engine.printBoard();
            assertTrue(true);
        }catch(BoardException ex){
            assertTrue(false);
        }
    }

    @Test
    public void TestGetCoordinateSymbol() throws BoardException {
        //arrange
        //act
        char symbol = engine.getCoordinateSymbol(1,1);
        //assert
        assertTrue(symbol=='.');


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
    public void TestIsBoardNotFull() throws BoardException {
        //arrange
        //act
        //assert
        assertFalse(engine.isBoardFull());
    }

    @Test
    public void TestForDiagonalWin() throws BoardException {
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
    public void TestForRowWin() throws BoardException {
        //arrange
        //act
        engine.placeMark(1,1);
        engine.endTurn();
        engine.placeMark(3,3);
        engine.endTurn();
        engine.placeMark(1,2);
        engine.endTurn();
        engine.placeMark(3,2);
        engine.endTurn();
        engine.placeMark(1,3);
        engine.endTurn();
        //assert
        engine.printBoard();
        assertTrue(engine.checkForWin());
    }

    @Test
    public void TestForColumnWin() throws BoardException {
        //arrange
        //act
        engine.placeMark(1,1);
        engine.placeMark(3,3);
        engine.placeMark(2,1);
        engine.placeMark(3,2);
        engine.placeMark(3,1);
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
        Exception ex = assertThrows(InputMismatchException.class,()->{
            handler.getInput(scanner,"x");
        });
        System.out.println(ex);
        assertNotNull(ex);
    }

    @Test
    public void aiMustPlaceMarkersTest() throws BoardException {
        GameEngine engine = new GameEngineImplementation(true);
        engine.initializeBoard();

        for (int i = 0; i < 9; i++) {
            engine.endTurn();
            engine.printBoard();
            System.out.println("");
            if (engine.checkForWin()){
                assertTrue(engine.checkForWin());
                break;
            }
        }
    }
}
