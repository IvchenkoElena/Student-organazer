package input;

import collection.CustomSingleList;
import model.Student;
import model.StudentBuilder;
import validation.Validation;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileInputStrategy implements InputStrategy {
    private static final String INPUT_FILE = "input.csv";
    private static List<Student> students = new CustomSingleList<>();

    @Override
    public List<Student> read(int count) {

        try (Stream<String> linesStream = Files.lines(Paths.get(INPUT_FILE))) {
            students = linesStream
                    .filter(this::checkValidation)
                    .limit(count)
                    .map(this::fromLineToStudent)
                    .collect(Collectors.toCollection(CustomSingleList::new));
        } catch (Exception e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }

        return students;
    }

    private boolean checkValidation(String line) {
        String[] parts = line.split(",");
        if (parts.length != 3) {
            System.out.println("Ошибка: неверные данные. Строка пропущена.");
        }
        int groupNumber = Integer.valueOf(parts[0]);
        double averageGrade = Double.valueOf(parts[1]);
        String recordBookNumber = parts[2];

        if(!Validation.isValidInputData(groupNumber, averageGrade, recordBookNumber)) {
            System.out.println("Пропущена запись с невалидными данными: " + groupNumber + ", " + averageGrade + ", " + recordBookNumber);
        }

        return Validation.isValidInputData(groupNumber,averageGrade, recordBookNumber);
    }

    private Student fromLineToStudent(String line) {
        String[] parts = line.split(",");
        if (parts.length != 3) {
            System.out.println("Ошибка: неверные данные. Строка пропущена.");
        }
        int groupNumber = Integer.valueOf(parts[0]);
        double averageGrade = Double.valueOf(parts[1]);
        String recordBookNumber = parts[2];

        return createStudent(groupNumber, averageGrade, recordBookNumber);
    }

    private Student createStudent(int groupNumber, double averageGrade, String recordBookNumber) {

        return new StudentBuilder()
                .setGroupNumber(groupNumber)
                .setAverageGrade(averageGrade)
                .setRecordBookNumber(recordBookNumber)
                .build();
    }
}
