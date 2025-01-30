
import com.connect4.Gameboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Gameboardtest {
    private Gameboard board;

    @BeforeEach
    void setUp() {
        board = new Gameboard(6, 7);
    }

    @Test
    void testDropDisc_ValidMove() {
        assertTrue(board.dropDisc(3, 'X'));
    }

    @Test
    void testDropDisc_ColumnFull() {
        for (int i = 0; i < 6; i++) {
            board.dropDisc(3, 'X');
        }
        assertFalse(board.dropDisc(3, 'O'));
    }

    @Test
    void testCheckWin_Horizontal() {
        for (int i = 0; i < 4; i++) {
            board.dropDisc(i, 'X');
        }
        assertTrue(board.checkWin('X'));
    }

    @Test
    void testCheckWin_Vertical() {
        for (int i = 0; i < 4; i++) {
            board.dropDisc(2, 'O');
        }
        assertTrue(board.checkWin('O'));
    }

    @Test
    void testCheckWin_Diagonal() {
        board.dropDisc(0, 'X');
        board.dropDisc(1, 'O');
        board.dropDisc(1, 'X');
        board.dropDisc(2, 'O');
        board.dropDisc(2, 'O');
        board.dropDisc(2, 'X');
        board.dropDisc(3, 'O');
        board.dropDisc(3, 'O');
        board.dropDisc(3, 'O');
        board.dropDisc(3, 'X');

        assertTrue(board.checkWin('X'));
    }

    @Test
    void testIsFull_FalseInitially() {
        assertFalse(board.isFull());
    }

    @Test
    void testIsFull_TrueWhenBoardIsFull() {
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                board.dropDisc(col, 'X');
            }
        }
        assertTrue(board.isFull());
    }
}
