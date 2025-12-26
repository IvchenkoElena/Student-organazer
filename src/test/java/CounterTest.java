import collection.CollectionFiller;
import collection.CustomSingleList;
import counter.ElementCounter;
import model.Student;
import model.StudentBuilder;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CounterTest {

    @Test
    void counterTest() {
        PrintStream originalOut = System.out; // 1. Сохраняем
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); // 2. Создаем буфер
        System.setOut(new PrintStream(baos)); // 3. Перенаправляем
        // 4. Выполняем код, который печатает в консоль

        Student student = new StudentBuilder()
                .setGroupNumber(101)
                .setAverageGrade(3.5)
                .setRecordBookNumber("HJ123456")
                .build();

        Student student2 = new StudentBuilder()
                .setGroupNumber(102)
                .setAverageGrade(4.9)
                .setRecordBookNumber("HK345678")
                .build();
        Student student3 = new StudentBuilder()
                .setGroupNumber(101)
                .setAverageGrade(4.5)
                .setRecordBookNumber("HY345678")
                .build();
        Student student4 = new StudentBuilder()
                .setGroupNumber(103)
                .setAverageGrade(2.1)
                .setRecordBookNumber("JK345678")
                .build();
        Student student5 = new StudentBuilder()
                .setGroupNumber(101)
                .setAverageGrade(2.1)
                .setRecordBookNumber("JP345678")
                .build();
        Student student6 = new StudentBuilder()
                .setGroupNumber(106)
                .setAverageGrade(5.0)
                .setRecordBookNumber("DF765432")
                .build();
        CustomSingleList<Student> list = CollectionFiller.fillStudents(student, student2, student3, student4, student5, student6);
        ElementCounter.countOccurrences(list, 101);

        System.setOut(originalOut); // 5. Восстанавливаем

        String output = baos.toString(); // 6. Получаем вывод
        String expected = "Количество студентов группы 101: 3\n"; // Ожидаемый результат
        assertEquals(expected, output); // 7. Проверяем
    }
}
