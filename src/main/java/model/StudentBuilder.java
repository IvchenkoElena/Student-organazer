package model;

public class StudentBuilder
{
    private String groupNumber;
    private double averageGrade;
    private String recordBookNumber;

    //Сеттеры
    public StudentBuilder setGroupNumber(String groupNumber)
    {
        this.groupNumber = groupNumber;
        return this;
    }

    public StudentBuilder setAverageGrade(double averageGrade)
    {
        this.averageGrade = averageGrade;
        return this;
    }

    public StudentBuilder setRecordBookNumber(String recordBookNumber)
    {
        this.recordBookNumber = recordBookNumber;
        return this;
    }

    public Student build()
    {
        return new Student(this);
    }

    //Геттеры
    public String getGroupNumber()
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
}
