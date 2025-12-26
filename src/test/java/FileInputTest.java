import input.FileInputStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileInputTest {
    private final FileInputStrategy strategy = new FileInputStrategy();

    @Test
    void equalsFileInputData() {
        assertEquals(7, strategy.read(7).size());
        assertEquals("ZK123456", strategy.read(1).getFirst().getRecordBookNumber());
    }
}
