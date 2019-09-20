package ru.siksmfp.learn.concurrency.intermediate.collection.copy_on_write_array_list;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Remove {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> numbers = new CopyOnWriteArrayList<>(new Integer[]{1, 3, 5, 8});

        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            iterator.remove(); //copy on write. Removing still is unsupported
        }
    }
}
