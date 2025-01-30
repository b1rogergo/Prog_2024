
import com.connect4.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Playertest {
    @Test
    void testPlayerCreation() {
        Player player = new Player("Anna", 'X');
        assertEquals("Anna", player.getName());
        assertEquals('X', player.getSymbol());
    }
}
