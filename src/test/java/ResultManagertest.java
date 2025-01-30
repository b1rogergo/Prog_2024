import com.connect4.ResultsManager;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class ResultManagertest {
    @Test
    void testSaveAndLoadResults() {
        ResultsManager.saveResult("Kalman", true);
        Map<String, Integer> results = ResultsManager.loadResults();
        assertTrue(results.containsKey("Kalman"));
        assertTrue(results.get("Kalman") > 0);
    }

    @Test
    void testResultsFileExistsAfterSaving() {
        ResultsManager.saveResult("Kalman", true);
        File resultsFile = new File("results.txt");
        assertTrue(resultsFile.exists());
    }
}
