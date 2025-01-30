
import com.connect4.WinnerManager;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class WinnerManagertest {
    @Test
    void testSaveAndLoadWinners() {
        WinnerManager.saveWinner("Test");
        List<String> winners = WinnerManager.loadWinners();
        assertTrue(winners.contains("Test"));
    }

    @Test
    void testWinnersFileExistsAfterSaving() {
        WinnerManager.saveWinner("Test");
        File winnersFile = new File("winners.txt");
        assertTrue(winnersFile.exists());
    }
}
