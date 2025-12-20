import input.ConsoleInputStrategy;
import input.FileInputStrategy;
import input.InputStrategy;
import input.RandomInputStrategy;
import model.Student;
import sorting.SortService;

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
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");


            int choice = scanner.nextInt();


            switch (choice) {
                case 1 -> inputData();
                case 2 -> sortAndDisplay();
                case 0 -> {
                    System.out.println("До свидания!");
                    return;
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void inputData() {
        System.out.println("Выберите источник данных:");
        System.out.println("1. Консоль");
        System.out.println("2. Файл input.csv");
        System.out.println("3. Случайные данные");
        System.out.print("Ваш выбор: ");


        int source = scanner.nextInt();
        System.out.print("Количество записей: ");
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
    }

    private void sortAndDisplay() {
        if (students.isEmpty()) {
            System.out.println("Нет данных для сортировки. Введите данные сначала.");
            return;
        }

        System.out.println("\nИсходные данные:");
        for (Student student : students) {
            System.out.println(student);
        }

        SortService.sort(students);

        System.out.println("Отсортированные данные:");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
