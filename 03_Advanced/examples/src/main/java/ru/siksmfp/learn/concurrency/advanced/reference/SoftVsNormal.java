package ru.siksmfp.learn.concurrency.advanced.reference;

// SoftReference: Soft reference objects are cleared at the discretion of
// the garbage collector in response to memory demand.
// Soft references are most often used to implement memory-sensitive caches.
// All soft references to softly reachable objects are guaranteed
// to have been cleared before the virtual machine throws an OutOfMemoryError.

// WeakReference: Weak reference objects do not prevent their referents
// from being made finalizable, finalized, and then reclaimed.
// Weak references are most often used to implement canonicalizing mappings.
// (Here, Canonicalizing mappings means mapping only reachable object instances.)

//  PhantomReference: Phantom reference objects are enqueued after the collector
//  determines that their referents may otherwise be reclaimed.
//  Phantom references are most often used for scheduling pre-mortem cleanup
//  actions in a more flexible way than is possible with the Java finalization mechanism.
//  Unlike soft and weak references, phantom references are not automatically
//  cleared by the garbage collector as they are enqueued.
//  An object that is reachable via phantom references will remain
//  so until all such references are cleared or themselves become unreachable.

import ru.siksmfp.learn.concurrency.advanced.Utils;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SoftVsNormal {

    public static void main(String[] args) {

//        -Xmx3m -Xms3m
//      The output shows that normal objects are garbage collected
//      but all soft references are still reachable till the end.
        List<Reference<MyObject>> references = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //create soft reference
            MyObject myObject = new MyObject("soft " + i);
            Reference<MyObject> ref = new SoftReference<>(myObject);
            references.add(ref);
            //without wrapping in any reference
            new MyObject("normal " + i);
        }
        //let see which ones' get() will return null
        printReferences(references);

        Utils.sleepSeconds(3);
        for (int i = 0; i < 300; i++) {
            new MyObject("normal " + i);
        }

        printReferences(references);
    }

    public static void printReferences(List<Reference<MyObject>> references) {
        ExecutorService ex = Executors.newSingleThreadExecutor();
        ex.execute(() -> {
            try {
                //sleep a little in case if finalizers are currently running
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-- printing references --");
            references
                    .forEach(SoftVsNormal::printReference);
        });
        ex.shutdown();
    }

    public static void printReference(Reference<MyObject> r) {
        System.out.printf("Reference: %s [%s]%n", r.get(),
                r.getClass().getSimpleName());
    }
}
