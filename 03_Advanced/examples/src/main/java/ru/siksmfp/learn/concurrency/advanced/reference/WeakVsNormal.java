package ru.siksmfp.learn.concurrency.advanced.reference;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

//WeakReference: The underlying object can be garbage collected just like a normal object.
// It can be useful for situation when an object is used multiple
// times repeatedly during an event, but then, will likely to be used after a long time
public class WeakVsNormal {
    public static void main(String[] args) {

        //This time most of the WeakReferences are garbage collected
        // as soon as they become unreachable, just like normal objects.
        List<Reference<MyObject>> references = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MyObject myObject = new MyObject("weak " + i);
            Reference<MyObject> ref = new WeakReference<>(myObject);
            references.add(ref);
            new MyObject("normal " + i);
        }
        SoftVsNormal.printReferences(references);
    }
}
//Let's look at the below example: We have an Map with Objects where Key is reference a object.
//Now, during the execution of the program we have made emp  = null.
// The Map holding the key makes no sense here as it is null.
// In the above situation, the object is not garbage collected.

//    public static void main(String args[]) {
//        HashMap<Employee, EmployeeVal> aMap = new
//                HashMap<Employee, EmployeeVal>();
//
//        Employee emp = new Employee("Vinoth");
//        EmployeeVal val = new EmployeeVal("Programmer");
//
//        aMap.put(emp, val);
//
//        emp = null;
//
//        System.gc();
//        System.out.println("Size of Map" + aMap.size());
//
//    }


//WeakHashMap
//WeakHashMap is one where the entries (key-to-value mappings)
// will be removed when it is no longer possible to retrieve them from the Map.

// WeakHashMap has only weak references to the keys,
// not strong references like other Map classes.
// There are situations which you have to take care when the value or
// key is strongly referenced though you have used WeakHashMap. This can avoided by wrapping the object in a WeakReference.
