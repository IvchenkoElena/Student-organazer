package input;

import collection.CustomSingleList;
import model.Student;
import model.StudentBuilder;
import validation.Validation;

import java.util.List;
import java.util.Scanner;

public class ConsoleInputStrategy implements InputStrategy {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Student> students = new CustomSingleList<>();

    @Override
    public List<Student> read(int count) {
        for (int i = 0; i < count; i++) {
            System.out.printf("Запись %d. Группа, балл, зачётка: ", i + 1);
            int groupNumber = 0;
            double averageGrade = 0.0;
            String recordBookNumber = "";

            if (scanner.hasNextInt()) groupNumber = scanner.nextInt();
            if (scanner.hasNext()) averageGrade = Double.parseDouble(scanner.next());
            if (scanner.hasNext()) recordBookNumber = scanner.next();

            addStudent(groupNumber, averageGrade, recordBookNumber);
        }
        return students;
    }

    private void addStudent(int groupNumber, double averageGrade, String recordBookNumber) {
        if (Validation.isValidInputData(groupNumber, averageGrade, recordBookNumber)) {
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
}
