package input;

import model.Student;

import java.util.List;

public interface InputStrategy {
    List<Student> read(int count);
}
