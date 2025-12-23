package counter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ElementCounter {

    public static void countOccurrences(List<Integer> list, int N, int threadCount) {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        AtomicInteger totalCount = new AtomicInteger(0);

        int chunkSize = list.size() / threadCount;

        for (int i = 0; i < threadCount; i++) {
            int start = i * chunkSize;
            int end = (i == threadCount - 1) ? list.size() : start + chunkSize;

            executor.execute(() -> {
                int localCount = 0;
                for (int j = start; j < end; j++) {
                    if (list.get(j) == N) {
                        localCount++;
                    }
                }
                totalCount.addAndGet(localCount);
            });
        }

        executor.shutdown();
        try {
            boolean finished = executor.awaitTermination(1, TimeUnit.MINUTES);

            if (!finished) {
                System.out.println("Не все потоки завершились вовремя");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }


        System.out.println("Количество вхождений элемента " + N + " : " + totalCount.get());
    }
}
