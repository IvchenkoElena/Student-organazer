package input;

import model.Student;
import model.StudentBuilder;
import validation.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInputStrategy implements InputStrategy {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public List<Student> read(int count) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            System.out.printf("Запись %d. Группа, балл, зачётка: ", i + 1);
            int groupNumber = scanner.nextInt();
            double averageGrade = scanner.nextDouble();
            String recordBookNumber = scanner.next();

            if (Validation.isValidStudent(groupNumber, averageGrade, recordBookNumber)) {
                Student student = new StudentBuilder()
                        .setGroupNumber(groupNumber)
                        .setAverageGrade(averageGrade)
                        .setRecordBookNumber(recordBookNumber)
                        .build();
                students.add(student);
            } else {
                System.out.println("Ошибка: неверные данные. Запись пропущена.");
            }
        }
        return students;
    }
}
