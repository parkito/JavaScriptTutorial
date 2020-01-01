package ru.siksmfp.learn.concurrency.advanced.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

//Phantom References: The objects which are being referenced by phantom references
// are eligible for garbage collection.
// But, before removing them from the memory,
// JVM puts them in a queue called ‘reference queue’.
// They are put in a reference queue after calling finalize() method on them.To create such references
public class PhantomReferenceExample {

    public static void main(String[] args) {
        //Strong Reference
        MyObject object = new MyObject("obj");

        //Creating reference queue
        ReferenceQueue<MyObject> refQueue = new ReferenceQueue<>();

        //Creating Phantom Reference to MyObject-type object to which 'object'
        //is also pointing.
        PhantomReference<MyObject> phantomRef;

        phantomRef = new PhantomReference<>(object, refQueue);
        System.out.println(refQueue.poll());

        //Now, MyObject-type object to which 'object' was pointing
        //earlier is available for garbage collection.
        //But, this object is kept in 'refQueue' before
        //removing it from the memory.
        object = null;
        System.out.println(refQueue.poll());

        //It always returns null.
        object = phantomRef.get();


        //It shows NullPointerException.
        object.toString();
    }
}
