import org.junit.jupiter.api.Test;
import validation.Validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestApp {
    private Validation validation = new Validation();

    @Test
    void notValidAllDataInput() {
        assertFalse(validation.isValidInputData(1000, 10.0, "656776755656"));
    }

    @Test
    void validAllDataInput(){
        assertTrue(validation.isValidInputData(300, 4.3, "LM887963"));
    }
}
