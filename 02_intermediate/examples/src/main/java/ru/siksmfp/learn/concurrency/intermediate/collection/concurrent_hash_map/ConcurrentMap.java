package ru.siksmfp.learn.concurrency.intermediate.collection.concurrent_hash_map;

//The following APIs are also overridden to support atomicity,
// without a default interface implementation:

//            putIfAbsent
//            remove
//            replace(key, oldValue, newValue)
//            replace(key, value)

//The table buckets are initialized lazily, upon the first insertion.
// Each bucket can be independently locked by locking the very first node in the bucket.
// Read operations do not block, and update contentions are minimized.

//Unlike most stream methods, the bulk (sequential and parallel)
// operations allow concurrent modification safely.
// ConcurrentModificationException won't be thrown,
// which also applies to its iterators. Relevant to streams, several forEach*, search,
// and reduce* methods are also added to support richer traversal and map-reduce operations.

import ru.siksmfp.learn.concurrency.intermediate.Utils;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Retrieval operations generally do not block in ConcurrentHashMap and could overlap with update operations.
// So for better performance, they only reflect the results of the most recently completed update operations.
public class ConcurrentMap {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

        executorService.submit(() -> {
            System.out.println("Iterating is started");
            Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();//as u see
            Utils.sleepSeconds(1);                                         //iterator might not see adding
            while (iterator.hasNext()) {
                Utils.sleepSeconds(1);
                System.out.println(iterator.next());
            }
            System.out.println("Iterating is done");
        });

        executorService.submit(() -> {
            System.out.println("Populating is started");
            for (int i = 0; i < 10; i++) {
                System.out.println("Put " + i);
                map.put(i, String.valueOf(i));
                Utils.sleepSeconds(1);
            }
        });

        executorService.shutdown();
    }
}
