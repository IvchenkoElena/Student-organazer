package sorting;

import model.Student;

import java.util.*;

public class SortService {
    public static void sort(List<Student> students) {
        sort(students, SortType.DEFAULT);
    }

    public static void sort(List<Student> students, SortType sortType) {
        if (students == null || students.size() <= 1) {
            return;
        }
        HashSet<Integer> freezeIndexes = getIndexesOfUnsortedElements(students, sortType);
        Student[] studentsArray = listToArray(students, freezeIndexes);
        // Создаем стек для хранения границ подмассивов, которые нужно отсортировать
        // Нужен для избегания рекурсии
        Deque<SortRange> stack = new ArrayDeque<>();
        // Добавляем весь массив в стек как начальный диапазон
        stack.push(new SortRange(0, studentsArray.length - 1));

        // Пока есть подмассивы для сортировки
        while (!stack.isEmpty()) {
            SortRange range = stack.pop();
            int left = range.left;
            int right = range.right;

            // Если подмассив имеет 1 элемент или меньше - он уже отсортирован
            if (left >= right) {
                continue;
            }

            // Выбираем опорный элемент (медиана из трех для улучшения производительности)
            int pivotIndex = selectPivot(studentsArray, left, right);
            Student pivot = studentsArray[pivotIndex];

            // Перемещаем опорный элемент в начало для удобства
            swap(studentsArray, pivotIndex, left);

            // Разделение массива относительно опорного элемента
            int i = left + 1; // указатель на элементы меньше опорного
            int j = right;    // указатель на элементы больше опорного

            while (i <= j) {
                // Ищем элемент, который должен быть справа от опорного
                while (i <= j && studentsArray[i].compareTo(pivot) <= 0) {
                    i++;
                }

                // Ищем элемент, который должен быть слева от опорного
                while (i <= j && studentsArray[j].compareTo(pivot) > 0) {
                    j--;
                }

                // Меняем местами неотсортированные элементы
                if (i < j) {
                    swap(studentsArray, i, j);
                }
            }

            // Возвращаем опорный элемент на правильную позицию
            swap(studentsArray, left, j);

            // Добавляем в стек левую часть (элементы меньше опорного)
            if (left < j - 1) {
                stack.push(new SortRange(left, j - 1));
            }

            // Добавляем в стек правую часть (элементы больше опорного)
            if (j + 1 < right) {
                stack.push(new SortRange(j + 1, right));
            }
        }

        refillingSortedList(students, studentsArray, freezeIndexes);
    }

    private static Student[] listToArray(List<Student> students, HashSet<Integer> freezeIndexes) {
        var result = new Student[students.size() - freezeIndexes.size()];
        var shift = 0;
        for (var i = 0; i < students.size(); i++) {
            if (freezeIndexes.contains(i)) {
                shift++;
            }
            else {
                result[i - shift] = students.get(i);
            }
        }
        return result;
    }

    private static HashSet<Integer> getIndexesOfUnsortedElements(List<Student> students, SortType sortType) {
        var result = new HashSet<Integer>();
        if (sortType == SortType.DEFAULT)
            return result;

        var index = -1;
        for (var student: students) {
            index++;
            if (student.getGroupNumber() % 2 != 0) {
                result.add(index);
            }
        }
        return result;
    }

    private static void refillingSortedList(
            List<Student> target,
            Student[] studentsArray,
            HashSet<Integer> freezeIndexes
    ) {
        var shift = 0;
        for (int i = 0; i < target.size(); i++) {
            if (freezeIndexes.contains(i)) {
                shift++;
            }
            else {
                target.set(i, studentsArray[i - shift]);
            }
        }
    }

    /**
     * Выбор опорного элемента (медиана из трех)
     * @param array массив студентов
     * @param left левая граница
     * @param right правая граница
     * @return индекс опорного элемента
     */
    private static int selectPivot(Student[] array, int left, int right) {
        int mid = left + (right - left) / 2;

        // Находим медиану из трех элементов, используя compareStudents
        if (array[left].compareTo(array[mid]) > 0) {
            swap(array, left, mid);
        }
        if (array[left].compareTo(array[right]) > 0) {
            swap(array, left, right);
        }
        if (array[mid].compareTo(array[right]) > 0) {
            swap(array, mid, right);
        }

        return mid;
    }

    /**
     * Поменять местами два элемента в массиве
     * @param array массив
     * @param i индекс первого элемента
     * @param j индекс второго элемента
     */
    private static void swap(Student[] array, int i, int j) {
        // Косяк может быть !!!
        Student temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
