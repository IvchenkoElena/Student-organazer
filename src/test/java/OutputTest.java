import collection.CollectionFiller;
import collection.CustomSingleList;
import model.Student;
import model.StudentBuilder;
import org.junit.jupiter.api.Test;
import output.OutputFileAppender;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputTest {

    @Test
    void appendStudentToOutputFile() throws IOException {
        Student student = new StudentBuilder()
                .setGroupNumber(101)
                .setAverageGrade(3.5)
                .setRecordBookNumber("FG123456")
                .build();
        CustomSingleList<Student> list = CollectionFiller.fillStudents(student);
        OutputFileAppender.appendStudents(list);

        List<String> lines = Files.readAllLines(Paths.get("output.csv"));
        String actualLastLine = lines.getLast();

        assertEquals("101,3.5,FG123456", actualLastLine, "Последняя строка не совпадает!");
    }
}
