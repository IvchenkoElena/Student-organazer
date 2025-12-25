import org.junit.jupiter.api.Test;
import validation.Validation;


import static org.junit.jupiter.api.Assertions.*;

public class ValidationTest {


    @Test
    void notValidAllDataInput() {
        assertFalse(Validation.isValidInputData(1000, 10.0, "656776755656"));
    }

    @Test
    void notValidGroupNumberInput() {
        assertFalse(Validation.isValidInputData(1000, 4.0, "LM887963"));
        assertFalse(Validation.isValidInputData(99, 4.0, "LM887963"));
    }

    @Test
    void notValidAverageGradeInput() {
        assertFalse(Validation.isValidInputData(100, 5.1, "LM887963"));
        assertFalse(Validation.isValidInputData(100, 1.9, "LM887963"));
    }

    @Test
    void notValidRecordBookNumberInput() {
        assertFalse(Validation.isValidInputData(100, 4.1, "lm887963"));
        assertFalse(Validation.isValidInputData(100, 2.9, "LM88763"));
    }

    @Test
    void validAllDataInput() {
        assertTrue(Validation.isValidInputData(300, 4.3, "LM887963"));
    }

}
