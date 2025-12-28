import input.FileInputStrategy;
import model.Student;
import model.StudentBuilder;
import org.junit.jupiter.api.Test;
import sorting.SortService;
import sorting.SortType;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SortingTest {
    private double delta = 0.0001;

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

    @Test
    public void testEmptyList() {
        List<Student> students = new ArrayList<>();
        SortService.sort(students);
        assertTrue(students.isEmpty());
    }

    @Test
    public void testSingleElement() {
        Student student = new StudentBuilder().setGroupNumber(1).setAverageGrade(4.5).setRecordBookNumber("202301").build();
        List<Student> students = new ArrayList<>();
        students.add(student);
        SortService.sort(students);
        assertEquals(1, students.size());
        assertSame(student, students.getFirst());
    }

    @Test
    public void testSortByAllFieldsAscending() {
        List<Student> students = Arrays.asList(
                new StudentBuilder().setGroupNumber(2).setAverageGrade(4.5).setRecordBookNumber("202302").build(),
                new StudentBuilder().setGroupNumber(1).setAverageGrade(4.8).setRecordBookNumber("202301").build(),
                new StudentBuilder().setGroupNumber(1).setAverageGrade(4.5).setRecordBookNumber("202303").build(),
                new StudentBuilder().setGroupNumber(2).setAverageGrade(4.2).setRecordBookNumber("202304").build(),
                new StudentBuilder().setGroupNumber(1).setAverageGrade(4.8).setRecordBookNumber("202300").build()
        );

        SortService.sort(students);
        assertAll(
                // Первая группа
                () -> assertEquals(1, students.getFirst().getGroupNumber()),
                () -> assertEquals(4.5, students.getFirst().getAverageGrade(), delta),
                () -> assertEquals("202303", students.getFirst().getRecordBookNumber()),

                () -> assertEquals(1, students.get(1).getGroupNumber()),
                () -> assertEquals(4.8, students.get(1).getAverageGrade(), delta),
                () -> assertEquals("202300", students.get(1).getRecordBookNumber()),

                () -> assertEquals(1, students.get(2).getGroupNumber()),
                () -> assertEquals(4.8, students.get(2).getAverageGrade(), delta),
                () -> assertEquals("202301", students.get(2).getRecordBookNumber()),

                // Вторая группа
                () -> assertEquals(2, students.get(3).getGroupNumber()),
                () -> assertEquals(4.2, students.get(3).getAverageGrade(), delta),
                () -> assertEquals("202304", students.get(3).getRecordBookNumber()),

                () -> assertEquals(2, students.get(4).getGroupNumber()),
                () -> assertEquals(4.5, students.get(4).getAverageGrade(), delta),
                () -> assertEquals("202302", students.get(4).getRecordBookNumber())
        );
    }

    @Test
    public void testSortWhenAllFieldsDifferent() {
        List<Student> students = Arrays.asList(
                new StudentBuilder().setGroupNumber(3).setAverageGrade(3.0).setRecordBookNumber("C003").build(),
                new StudentBuilder().setGroupNumber(1).setAverageGrade(5.0).setRecordBookNumber("A001").build(),
                new StudentBuilder().setGroupNumber(2).setAverageGrade(4.0).setRecordBookNumber("B002").build(),
                new StudentBuilder().setGroupNumber(1).setAverageGrade(4.0).setRecordBookNumber("A002").build()
        );

        SortService.sort(students);

        assertAll(
                () -> assertEquals(1, students.getFirst().getGroupNumber()),

                () -> assertEquals(4.0, students.getFirst().getAverageGrade(), delta),
                () -> assertEquals(5.0, students.get(1).getAverageGrade(), delta),

                () -> assertEquals(2, students.get(2).getGroupNumber()),
                () -> assertEquals(4.0, students.get(2).getAverageGrade(), delta),

                () -> assertEquals(3, students.get(3).getGroupNumber()),
                () -> assertEquals(3.0, students.get(3).getAverageGrade(), delta)
        );
    }

    @Test
    public void testAlreadySortedList() {
        List<Student> students = new ArrayList<>();
        students.add(new StudentBuilder().setGroupNumber(1).setAverageGrade(3.5).setRecordBookNumber("001").build());
        students.add(new StudentBuilder().setGroupNumber(1).setAverageGrade(4.0).setRecordBookNumber("002").build());
        students.add(new StudentBuilder().setGroupNumber(2).setAverageGrade(4.2).setRecordBookNumber("003").build());
        students.add(new StudentBuilder().setGroupNumber(3).setAverageGrade(5.0).setRecordBookNumber("004").build());

        List<Student> original = new ArrayList<>(students);
        SortService.sort(students);

        for (int i = 0; i < students.size(); i++) {
            assertEquals(original.get(i), students.get(i));
        }
    }

    @Test
    public void testReverseSortedList() {
        List<Student> students = Arrays.asList(
                new StudentBuilder().setGroupNumber(3).setAverageGrade(5.0).setRecordBookNumber("999").build(),
                new StudentBuilder().setGroupNumber(3).setAverageGrade(4.5).setRecordBookNumber("888").build(),
                new StudentBuilder().setGroupNumber(2).setAverageGrade(4.8).setRecordBookNumber("777").build(),
                new StudentBuilder().setGroupNumber(2).setAverageGrade(3.8).setRecordBookNumber("666").build(),
                new StudentBuilder().setGroupNumber(1).setAverageGrade(4.0).setRecordBookNumber("555").build(),
                new StudentBuilder().setGroupNumber(1).setAverageGrade(3.5).setRecordBookNumber("444").build()
        );

        SortService.sort(students);

        // Проверяем общие условия сортировки
        for (int i = 0; i < students.size() - 1; i++) {
            Student current = students.get(i);
            Student next = students.get(i + 1);
            assertTrue(current.getGroupNumber() <= next.getGroupNumber());
            if (current.getGroupNumber() == next.getGroupNumber()) {
                assertTrue(current.getAverageGrade() <= next.getAverageGrade());
                if (Math.abs(current.getAverageGrade() - next.getAverageGrade()) < delta) {
                    assertTrue(current.getRecordBookNumber().compareTo(next.getRecordBookNumber()) <= 0);
                }
            }
        }
    }

    @Test
    public void testListWithAllSameGroup() {
        List<Student> students = Arrays.asList(
                new StudentBuilder().setGroupNumber(5).setAverageGrade(4.8).setRecordBookNumber("005").build(),
                new StudentBuilder().setGroupNumber(5).setAverageGrade(4.2).setRecordBookNumber("003").build(),
                new StudentBuilder().setGroupNumber(5).setAverageGrade(4.5).setRecordBookNumber("001").build(),
                new StudentBuilder().setGroupNumber(5).setAverageGrade(4.2).setRecordBookNumber("002").build(),
                new StudentBuilder().setGroupNumber(5).setAverageGrade(4.8).setRecordBookNumber("004").build()
        );

        SortService.sort(students);
        assertAll(
                () -> assertEquals(4.2, students.getFirst().getAverageGrade(), delta),
                () -> assertEquals("002", students.getFirst().getRecordBookNumber()),

                () -> assertEquals(4.2, students.get(1).getAverageGrade(), delta),
                () -> assertEquals("003", students.get(1).getRecordBookNumber()),

                () -> assertEquals(4.5, students.get(2).getAverageGrade(), delta),
                () -> assertEquals("001", students.get(2).getRecordBookNumber()),

                () -> assertEquals(4.8, students.get(3).getAverageGrade(), delta),
                () -> assertEquals("004", students.get(3).getRecordBookNumber()),

                () -> assertEquals(4.8, students.get(4).getAverageGrade(), delta),
                () -> assertEquals("005", students.get(4).getRecordBookNumber())
        );
    }

    @Test
    public void testListWithDuplicateStudents() {
        Student student1 = new StudentBuilder()
                .setGroupNumber(1)
                .setAverageGrade(4.5)
                .setRecordBookNumber("123")
                .build();

        Student student2 = new StudentBuilder()
                .setGroupNumber(1)
                .setAverageGrade(4.5)
                .setRecordBookNumber("123")
                .build();

        List<Student> students = Arrays.asList(
                new StudentBuilder().setGroupNumber(2).setAverageGrade(4.0).setRecordBookNumber("200").build(),
                student1,
                new StudentBuilder().setGroupNumber(1).setAverageGrade(4.0).setRecordBookNumber("100").build(),
                student2,
                new StudentBuilder().setGroupNumber(1).setAverageGrade(5.0).setRecordBookNumber("150").build()
        );

        SortService.sort(students);
        assertEquals(5, students.size());

        assertAll(
                () -> assertEquals(1, students.getFirst().getGroupNumber()),
                () -> assertEquals(4.0, students.getFirst().getAverageGrade(), delta),

                () -> assertEquals(1, students.get(1).getGroupNumber()),
                () -> assertEquals(4.5, students.get(1).getAverageGrade(), delta),

                () -> assertEquals(1, students.get(2).getGroupNumber()),
                () -> assertEquals(4.5, students.get(2).getAverageGrade(), delta),

                () -> assertEquals(1, students.get(3).getGroupNumber()),
                () -> assertEquals(5.0, students.get(3).getAverageGrade(), delta),

                () -> assertEquals(2, students.get(4).getGroupNumber()),
                () -> assertEquals(4.0, students.get(4).getAverageGrade(), delta)
        );
    }
}
