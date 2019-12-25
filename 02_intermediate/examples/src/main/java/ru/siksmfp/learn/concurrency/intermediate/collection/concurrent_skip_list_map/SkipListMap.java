package ru.siksmfp.learn.concurrency.intermediate.collection.concurrent_skip_list_map;

import ru.siksmfp.learn.concurrency.intermediate.Utils;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ConcurrentHashMap does not guarantee* the runtime of its operations as part of its contract.
 * It also allows tuning for certain load factors (roughly, the number of threads concurrently modifying it).
 *
 * ConcurrentSkipListMap, on the other hand, guarantees average O(log(n)) performance on a wide variety of operations.
 * It also does not support tuning for concurrency's sake.
 * ConcurrentSkipListMap also has a number of operations that ConcurrentHashMap doesn't:
 * ceilingEntry/Key, floorEntry/Key, etc.
 * It also maintains a sort order, which would otherwise have to be calculated (at notable expense)
 * if you were using a ConcurrentHashMap.
 *
 * Basically, different implementations are provided for different use cases.
 * If you need quick single key/value pair addition and quick single key lookup, use the HashMap.
 * If you need faster in-order traversal, and can afford the extra cost for insertion, use the SkipListMap
 */
public class SkipListMap {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();

        executorService.submit(() -> {
            System.out.println("Populating is started");
            for (int i = 0; i < 10; i++) {
                System.out.println("Put " + i);
                map.put(i, String.valueOf(i));
                Utils.sleepSeconds(2);
            }
        });

        executorService.submit(() -> {
            for (int i = 0; i < 10; i++) {
                Utils.sleepSeconds(3);
                System.out.println(map.keySet());
            }
        });

        executorService.shutdown();

    }
}
