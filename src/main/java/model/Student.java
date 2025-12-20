package model;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class Student implements Comparable<Student>
{

    private final int groupNumber;
    private final double averageGrade;
    private final String recordBookNumber;

    Student(int groupNumber, double averageGrade,@NotNull String recordBookNumber)
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Student otherStudent))
            return false;
        return this.groupNumber == otherStudent.groupNumber &&
                this.averageGrade == otherStudent.averageGrade &&
                Objects.equals(this.recordBookNumber, otherStudent.recordBookNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupNumber, averageGrade, recordBookNumber);
    }

    @Override
    public int compareTo(Student otherStudent) {
        if (this.groupNumber < otherStudent.groupNumber) {
            return -1;
        }
        else if (this.groupNumber > otherStudent.groupNumber) {
            return 1;
        }

        if (this.averageGrade < otherStudent.averageGrade) {
            return -1;
        }
        else if (this.averageGrade > otherStudent.averageGrade) {
            return 1;
        }
        return this.recordBookNumber.compareTo(otherStudent.getRecordBookNumber());
    }
}
