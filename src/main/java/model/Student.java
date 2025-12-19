package model;

public final class Student
{

    private final String groupNumber;
    private final double averageGrade;
    private final String recordBookNumber;

    //Конструктор принимает Builder
    public Student(StudentBuilder studentBuilder)
    {
        this.groupNumber = studentBuilder.getGroupNumber();
        this.averageGrade = studentBuilder.getAverageGrade();
        this.recordBookNumber = studentBuilder.getRecordBookNumber();
    }

    @Override
    public String toString()
    {
        return "Студент:" +
                " \nНомер группы = " + groupNumber +
                ", \nСредний балл = " + averageGrade +
                ", \nНомер зачетной книжки = " + recordBookNumber +".";
    }
}
