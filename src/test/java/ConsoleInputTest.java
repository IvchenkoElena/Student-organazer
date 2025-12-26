import input.ConsoleInputStrategy;
import model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleInputTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;// сохраним оригинал
    private ConsoleInputStrategy strategy;

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);// восстанавливаем
    }

    @Test
    void read_twoCorrectLines_twoStudentsCreated() {
        byte[] validData = "101 4.8 AP456123\n102 5.0 BL123456\n".getBytes(StandardCharsets.UTF_8);
        System.setIn(new ByteArrayInputStream(validData));
        strategy = new ConsoleInputStrategy();

        List<Student> res = strategy.read(2);

        assertEquals(2, res.size());
        assertEquals(101, res.getFirst().getGroupNumber());
        assertEquals(4.8, res.getFirst().getAverageGrade());
        assertEquals("AP456123", res.getFirst().getRecordBookNumber());

        assertEquals(102, res.getLast().getGroupNumber());
        assertEquals(5.0, res.getLast().getAverageGrade());
        assertEquals("BL123456", res.getLast().getRecordBookNumber());

    }

    @Test
    void notValidData() {
        byte[] notValidData = "101 1.9 KK345678\n".getBytes(StandardCharsets.UTF_8);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        System.setIn(new ByteArrayInputStream(notValidData));
        System.setOut(new PrintStream(baos));

        strategy = new ConsoleInputStrategy();

        strategy.read(1);

        String output = baos.toString();
        String expected = "Запись 1. Группа, балл, зачётка: Ошибка: неверные данные. Запись пропущена.\n";
        assertEquals(expected, output);


    }
}
