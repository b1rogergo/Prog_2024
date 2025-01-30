
import com.connect4.Gameboard;
import com.connect4.GameStateManager;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class GameStateManagertest {
    @Test
    void testSaveAndLoadGame() {
        Gameboard board = new Gameboard(6, 7);
        GameStateManager.saveGame(board);
        Gameboard loadedBoard = GameStateManager.loadGame(6, 7);
        assertNotNull(loadedBoard);
    }

    @Test
    void testSaveFileExistsAfterSaving() {
        Gameboard board = new Gameboard(6, 7);
        GameStateManager.saveGame(board);
        File saveFile = new File("game_state.txt");
        assertTrue(saveFile.exists());
    }
}
