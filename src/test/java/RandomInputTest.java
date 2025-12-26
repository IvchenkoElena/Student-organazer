import input.RandomInputStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RandomInputTest {
    private final RandomInputStrategy strategy = new RandomInputStrategy();

    @Test
    void equalsSizeRandomInput() {
        assertEquals(3, strategy.read(3).size());
    }
}