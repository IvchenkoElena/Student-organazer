package collection;

import model.Student;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionFiller {
    private CollectionFiller(){

    }
    /**
     * Заполняет кастомную коллекцию студентов через Stream API.
     * @param students массив студентов для заполнения или один объект студента
     * @return заполненная кастомная коллекция
     */
    public static CustomSingleList<Student> fillStudents(Student ...students){
        return Stream.of(students)
                .collect(Collectors.toCollection(CustomSingleList::new));
    }




}

