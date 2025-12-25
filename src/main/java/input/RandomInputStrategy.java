package input;

import collection.CustomSingleList;
import model.Student;
import model.StudentBuilder;
import validation.Validation;

import java.util.List;
import java.util.Random;

public class RandomInputStrategy implements InputStrategy {
    private static final Random RANDOM = new Random();
    private static final List<Student> students = new CustomSingleList<>();
    @Override
    public List<Student> read(int count) {
        for (int i = 0; i < count; i++) {
            int groupNumber = 100 + RANDOM.nextInt(900);
            double averageGrade = Math.round((2.0 + RANDOM.nextDouble() * (5.0 - 2.0)) * 10) / 10.0;

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 2; j++) {
                char ch = (char) ('A' + RANDOM.nextInt(26));
                sb.append(ch);
            }
            int x = RANDOM.nextInt(99999) + 100001;
            sb.append(x);
            String recordBookNumber = sb.toString();

            //проверка значений
            System.out.println(groupNumber);
            System.out.println(averageGrade);
            System.out.println(recordBookNumber);

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
