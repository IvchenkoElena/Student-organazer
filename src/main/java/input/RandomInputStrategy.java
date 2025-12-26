package input;

import collection.CollectionFiller;
import model.Student;
import model.StudentBuilder;

import java.util.List;
import java.util.Random;

public class RandomInputStrategy implements InputStrategy {
    private static final Random RANDOM = new Random();

    @Override
    public List<Student> read(int count) {
        Student[] stuArr = new Student[count];
        for (int i = 0; i < count; i++) {
            stuArr[i] = createRandomStudent();
        }

        return CollectionFiller.fillStudents(stuArr);
    }


    private Student createRandomStudent() {
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

        return new StudentBuilder()
                .setGroupNumber(groupNumber)
                .setAverageGrade(averageGrade)
                .setRecordBookNumber(recordBookNumber)
                .build();
    }
}
