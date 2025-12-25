import counter.ElementCounter;
import input.ConsoleInputStrategy;
import input.FileInputStrategy;
import input.InputStrategy;
import input.RandomInputStrategy;
import model.Student;
import output.OutputFileAppender;
import sorting.SortService;
import sorting.SortType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StuOrgApp {
    private final Scanner scanner = new Scanner(System.in);
    private List<Student> students = new ArrayList<>();


    public void run() {
        System.out.println("=== Сортировка студентов ===");


        while (true) {
            System.out.println("\n1. Ввести данные");
            System.out.println("2. Отсортировать студентов по всем полям");
            System.out.println("3. Узнать количество студентов в группе");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");


            int choice = scanner.nextInt();


            switch (choice) {
                case 1 -> inputData();
                case 2 -> sortAndDisplay();
                case 3 -> countGroupMembers();
                case 0 -> {
                    scanner.close();
                    System.out.println("До свидания!");
                    return;
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void countGroupMembers() {
        if (students.isEmpty()) {
            System.out.println("Нет данных для поиска. Введите данные сначала.");
            return;
        }
        System.out.println("\nВведите номер группы:");
        int groupNumber = scanner.nextInt();
        ElementCounter.countOccurrences(students, groupNumber);
    }

    private void inputData() {
        System.out.println("\nВыберите источник данных:");
        System.out.println("1. Консоль");
        System.out.println("2. Файл input.csv");
        System.out.println("3. Случайные данные");
        System.out.print("Ваш выбор: ");


        int source = scanner.nextInt();
        System.out.print("\nКоличество записей: ");
        int count = scanner.nextInt();


        InputStrategy strategy;
        switch (source) {
            case 1 -> strategy = new ConsoleInputStrategy();
            case 2 -> strategy = new FileInputStrategy();
            case 3 -> strategy = new RandomInputStrategy();
            default -> {
                System.out.println("Неверный источник. Данные не загружены.");
                return;
            }
        }

        students = strategy.read(count);
        System.out.printf("Загружено %d записей.\n", students.size());

        System.out.println("\nИсходные данные:");
        int i = 1;
        for (Student student : students) {
            System.out.println("\nЗапись " + i);
            System.out.println(student);
            i++;
        }
    }

    private void sortAndDisplay() {
        if (students.isEmpty()) {
            System.out.println("Нет данных для сортировки. Введите данные сначала.");
            return;
        }

        System.out.println("\nВыбеите способ сортировки данных:");
        System.out.println("1. Обычная");
        System.out.println("2. Необычная");
        System.out.print("Ваш выбор: ");

        int sortType = scanner.nextInt();

        switch (sortType) {
            case 1 -> SortService.sort(students);
            case 2 -> SortService.sort(students, SortType.INCREDIBLE);
            default -> System.out.println("Неверный выбор. Попробуйте снова.");
        }

        System.out.println("\nОтсортированные данные:");
        int i = 1;
        for (Student student : students) {
            System.out.println("\nЗапись " + i);
            System.out.println(student);
            i++;
        }

        try {
            OutputFileAppender.appendStudents(students);
            System.out.println("Данные добавлены в файл");
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл");
        }
    }
}
