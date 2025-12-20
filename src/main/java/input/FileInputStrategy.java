package input;

import model.Student;
import model.StudentBuilder;
import validation.Validation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileInputStrategy implements InputStrategy {
    private static final String INPUT_FILE = "input.csv";

    @Override
    public List<Student> read(int count) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            int linesRead = 0;
            String line;
            while ((line = reader.readLine()) != null && linesRead < count) {
                String[] parts = line.split(",");
                if (parts.length != 3) continue;

                int groupNumber = Integer.valueOf(parts[0]);
                double averageGrade = Double.valueOf(parts[1]);
                String recordBookNumber = parts[2];

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
                linesRead++;
            }

        } catch (Exception e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }

        return students;
    }
}
