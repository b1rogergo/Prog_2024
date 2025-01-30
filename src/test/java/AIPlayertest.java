
import com.connect4.Gameboard;
import com.connect4.AIPlayer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AIPlayertest {
    @Test
    void testAIPlayerMakesMove() {
        AIPlayer ai = new AIPlayer('O');
        Gameboard board = new Gameboard(6, 7);
        int move = ai.makeMove(board);
        assertTrue(move >= 0 && move < board.getCols());
    }
}
