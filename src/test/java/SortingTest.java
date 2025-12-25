import model.Student;
import model.StudentBuilder;
import org.junit.jupiter.api.Test;
import sorting.SortService;
import sorting.SortType;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SortingTest {

    @Test
    void testSort() {
        Student student = new StudentBuilder()
                .setGroupNumber(84)
                .setAverageGrade(4.9)
                .setRecordBookNumber("84")
                .build();

        Student student2 = new StudentBuilder()
                .setGroupNumber(4)
                .setAverageGrade(14.9)
                .setRecordBookNumber("8422")
                .build();
        Student student3 = new StudentBuilder()
                .setGroupNumber(4)
                .setAverageGrade(14.9)
                .setRecordBookNumber("8423")
                .build();
        Student student4 = new StudentBuilder()
                .setGroupNumber(4)
                .setAverageGrade(13.9)
                .setRecordBookNumber("SAA")
                .build();
        Student student5 = new StudentBuilder()
                .setGroupNumber(1)
                .setAverageGrade(14.9)
                .setRecordBookNumber("8422")
                .build();
        Student student6 = new StudentBuilder()
                .setGroupNumber(1)
                .setAverageGrade(-14.9)
                .setRecordBookNumber("8sd422")
                .build();
        var list = Arrays.asList(student, student2, student3, student4, student5, student6);
        SortService.sort(list);
        assertEquals(list.getFirst(), student6);
        list = Arrays.asList(student, student2, student3, student4, student5, student6);
        SortService.sort(list, SortType.INCREDIBLE);
        assertEquals(list.getLast(), student6);
    }
}
