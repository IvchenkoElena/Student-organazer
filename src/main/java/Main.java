import model.Student;
import model.StudentBuilder;

public class Main
{
    public static void main(String[] args)
    {

        Student student = new StudentBuilder()
                .setGroupNumber("Aston84поток")
                .setAverageGrade(4.9)
                .setRecordBookNumber("84")
                .build();

        System.out.println(student);
    }
}
