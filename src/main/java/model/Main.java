import model.Student;
import model.StudentBuilder;

public class Main
{
    public static void main(String[] args)
    {
    run();
    }

        public static void run()
        {
        Student student = new StudentBuilder()
                .setGroupNumber(84)
                .setAverageGrade(4.9)
                .setRecordBookNumber("84")
                .build();

        System.out.println(student);
        }
}
