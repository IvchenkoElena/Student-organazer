package model;

public class StudentBuilder {
    private int groupNumber;
    private double averageGrade;
    private String recordBookNumber;

    //Сеттеры
    public StudentBuilder setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
        return this;
    }

    public StudentBuilder setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
        return this;
    }

    public StudentBuilder setRecordBookNumber(String recordBookNumber) {
        this.recordBookNumber = recordBookNumber;
        return this;
    }

    public Student build() {
        return new Student(groupNumber, averageGrade, recordBookNumber);
    }
}

