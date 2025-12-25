import collection.CollectionFiller;
import collection.CustomSingleList;
import model.Student;
import model.StudentBuilder;
import org.junit.jupiter.api.Test;
import validation.Validation;


import static org.junit.jupiter.api.Assertions.*;

public class ValidationTest {


    @Test
    void notValidAllDataInput() {
        assertFalse(Validation.isValidInputData(1000, 10.0, "656776755656"));
    }

    @Test
    void validAllDataInput() {
        assertTrue(Validation.isValidInputData(300, 4.3, "LM887963"));
    }


    @Test
    void testFillCollection() {
        Student student = new StudentBuilder()
                .setGroupNumber(10)
                .setAverageGrade(19)
                .setRecordBookNumber("jhg")
                .build();

        Student student2 = new StudentBuilder()
                .setGroupNumber(5)
                .setAverageGrade(4.9)
                .setRecordBookNumber("435456")
                .build();
        Student student3 = new StudentBuilder()
                .setGroupNumber(3)
                .setAverageGrade(2)
                .setRecordBookNumber("nvv")
                .build();
        Student student4 = new StudentBuilder()
                .setGroupNumber(1)
                .setAverageGrade(2.1)
                .setRecordBookNumber("Gc")
                .build();
        Student student5 = new StudentBuilder()
                .setGroupNumber(0)
                .setAverageGrade(2.1)
                .setRecordBookNumber("fgdfgds")
                .build();
        Student student6 = new StudentBuilder()
                .setGroupNumber(0)
                .setAverageGrade(0)
                .setRecordBookNumber("dfsgfgsfg")
                .build();
        CustomSingleList<Student> list = CollectionFiller.fillStudents(student, student2, student3, student4, student5, student6);
        assertFalse(list.isEmpty());
    }
}
