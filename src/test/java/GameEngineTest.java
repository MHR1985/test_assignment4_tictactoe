import exceptions.BoardException;
import game.GameEngineImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GameEngineTest {

    @Test
    public void TestGameEngineNotNull(){
        //arrange
        GameEngineImplementation engine = new GameEngineImplementation(false);
        //act
        //assert
        assert(engine!=null);
    }

    @Test
    public void TestPrintBoardThrowsExceptionWhenBoardHasNotBeenInitialized(){
        //arrange
        GameEngineImplementation engine = new GameEngineImplementation(false);
        //act
        //assert
        Exception ex = assertThrows(BoardException.class,()->{
           engine.printBoard();
        });
        assert(ex!=null);


    }

    @Test
    public void TestBoardHasBeenInitialized() throws BoardException {
        //arrange
        GameEngineImplementation engine = new GameEngineImplementation(false);
        //act
        engine.initializeBoard();
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
        GameEngineImplementation engine = new GameEngineImplementation(false);
        //act
        engine.initializeBoard();
        char symbol = engine.getCoordinateSymbol(1,1);
        //assert
        assertTrue(symbol=='.');


    }

    @Test
    public void TestMarkHasBeenPlaced() throws BoardException {
        //arrange
        GameEngineImplementation engine = new GameEngineImplementation(false);
        //act
        engine.initializeBoard();
        engine.placeMark(1,1);
        char symbol = engine.getCoordinateSymbol(1,1);
        //assert
        assertTrue(symbol=='X'||symbol=='O');
    }

    @Test
    public void TestIsBoardFull() throws BoardException {
        //arrange
        GameEngineImplementation engine = new GameEngineImplementation(false);
        //act
        engine.initializeBoard();
        engine.placeMark(1,1);
        engine.placeMark(1,2);
        engine.placeMark(1,3);
        engine.placeMark(2,1);
        engine.placeMark(2,2);
        engine.placeMark(2,3);
        engine.placeMark(3,1);
        engine.placeMark(3,2);
        engine.placeMark(3,3);
        //assert
        engine.printBoard();
        assertTrue(engine.isBoardFull());
    }

    @Test
    public void TestCannotPlaceMarkerOnAlreadyMarkedField() throws BoardException {
        //arrange
        GameEngineImplementation engine = new GameEngineImplementation(false);
        //act
        engine.initializeBoard();
        engine.placeMark(1,1);
        //assert
        assertFalse(engine.placeMark(1,1));
    }

    @Test
    public void TestIsBoardNotFull() throws BoardException {
        //arrange
        GameEngineImplementation engine = new GameEngineImplementation(false);
        //act
        engine.initializeBoard();
        //assert
        assertFalse(engine.isBoardFull());
    }

    @Test
    public void TestForDiagonalWin() throws BoardException {
        //arrange
        GameEngineImplementation engine = new GameEngineImplementation(false);
        //act
        engine.initializeBoard();
        engine.placeMark(1,1);
        engine.placeMark(1,2);
        engine.placeMark(1,3);
        engine.placeMark(2,1);
        engine.placeMark(2,2);
        engine.placeMark(2,3);
        engine.placeMark(3,1);
        engine.placeMark(3,2);
        engine.placeMark(3,3);
        //assert
        engine.printBoard();
        assertTrue(engine.checkForWin());
    }

    @Test
    public void TestForRowWin() throws BoardException {
        //arrange
        GameEngineImplementation engine = new GameEngineImplementation(false);
        //act
        engine.initializeBoard();
        engine.placeMark(1,1);
        engine.placeMark(3,3);
        engine.placeMark(1,2);
        engine.placeMark(3,2);
        engine.placeMark(1,3);
        //assert
        engine.printBoard();
        assertTrue(engine.checkForWin());
    }

    @Test
    public void TestForColumnWin() throws BoardException {
        //arrange
        GameEngineImplementation engine = new GameEngineImplementation(false);
        //act
        engine.initializeBoard();
        engine.placeMark(1,1);
        engine.placeMark(3,3);
        engine.placeMark(2,1);
        engine.placeMark(3,2);
        engine.placeMark(3,1);
        //assert
        engine.printBoard();
        assertTrue(engine.checkForWin());
    }


}
