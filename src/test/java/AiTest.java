import exceptions.BoardException;
import game.GameEngineImplementation;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AiTest {

    private GameEngineImplementation engine;

    @BeforeEach
    public void setup() {
        engine = new GameEngineImplementation(true);
    }

    @Test
    public void aiMustPlaceMarkersTest() throws BoardException {
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
