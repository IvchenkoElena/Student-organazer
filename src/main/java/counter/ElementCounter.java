package counter;

import model.Student;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ElementCounter {

    public static void countOccurrences(List<Student> students, int groupNumber) {

        int size = students.size();
        if (size == 0) {
            System.out.println("Список пуст.");
            return;
        }

        int threadCount = Math.min(
                Runtime.getRuntime().availableProcessors(),
                (size + 99) / 100                // не более 1 потока на 100 элементов
        );
        if (threadCount < 1) threadCount = 1;

        AtomicInteger totalCount = new AtomicInteger(0);

        List<Integer> groupNumbersList = students.stream()
                .map(Student::getGroupNumber)
                .toList();

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        try {
            int chunkSize = groupNumbersList.size() / threadCount;

            for (int i = 0; i < threadCount; i++) {
                int start = i * chunkSize;
                int end = (i == threadCount - 1) ? groupNumbersList.size() : start + chunkSize;

                executor.execute(() -> {
                    int localCount = 0;
                    for (int j = start; j < end; j++) {
                        if (groupNumbersList.get(j) == groupNumber) {
                            localCount++;
                        }
                    }
                    totalCount.addAndGet(localCount);
                });
            }
        } finally {
            executor.shutdown();
            try {
                boolean finished = executor.awaitTermination(1, TimeUnit.MINUTES);

                if (!finished) {
                    System.out.println("Не все потоки завершились вовремя");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Количество студентов группы " + groupNumber + " : " + totalCount.get());
    }
}
