package model;

public final class Student
{

    private final int groupNumber;
    private final double averageGrade;
    private final String recordBookNumber;

    Student(int groupNumber, double averageGrade, String recordBookNumber)
    {
        this.groupNumber = groupNumber;
        this.averageGrade = averageGrade;
        this.recordBookNumber = recordBookNumber;
    }

    public int getGroupNumber()
    {
        return groupNumber;
    }

    public double getAverageGrade()
    {
        return averageGrade;
    }

    public String getRecordBookNumber()
    {
        return recordBookNumber;
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
