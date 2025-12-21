package output;

import model.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public final class OutputFileAppender {
    private static final String OUTPUT_FILE = "output.csv";

    public static void appendStudents(List<Student> students) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (Student s : students) {
            sb.append(s.getGroupNumber()).append(",").append(s.getAverageGrade()).append(",")
                    .append(s.getRecordBookNumber()).append("\n");
        }
        Files.writeString(Paths.get(OUTPUT_FILE),
                sb.toString(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }
}
