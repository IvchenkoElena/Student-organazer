package input;

import model.Student;
import model.StudentBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomInputStrategy implements InputStrategy {
    private static final Random RANDOM = new Random();
    @Override
    public List<Student> read(int count) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int groupNumber = RANDOM.nextInt(999) + 1;
            double averageGrade = 2.0 + RANDOM.nextDouble() * (5.0 - 2.0);

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 2; j++) {
                char ch = (char) ('A' + RANDOM.nextInt(26));
                sb.append(ch);
            }
            int x = RANDOM.nextInt(999999) + 1;
            sb.append(x);
            String recordBookNumber = sb.toString();

            Student student = new StudentBuilder()
                    .setGroupNumber(groupNumber)
                    .setAverageGrade(averageGrade)
                    .setRecordBookNumber(recordBookNumber)
                    .build();
            students.add(student);
        }

        return students;
    }
}
