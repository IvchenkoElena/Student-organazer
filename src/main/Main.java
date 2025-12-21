import model.Student;
import model.StudentBuilder;
import java.util.List;
import java.util.Arrays;
import counter.ElementCounter;

public class Main
{
    public static void main(String[] args) {
      StuOrgApp app = new StuOrgApp();
      app.run();
                List<Integer> numbers = Arrays.asList
                        (
                        1, 2, 3, 4, 2, 5, 2, 6, 7, 2, 8, 2, 9
                        );

                ElementCounter.countOccurrences(numbers, 2, 3);
            }
        }
