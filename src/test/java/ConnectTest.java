
import com.connect4.Gameboard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectTest {

    @Test
    public void testGameBoardInitialization() {
        Gameboard board = new Gameboard(6, 7);
        assertNotNull(board);
    }

    @Test
    public void testPlayerMove() {
        Gameboard board = new Gameboard(6, 7);
        assertTrue(board.dropDisc(3, 'X'));
    }
}
