package ru.siksmfp.learn.concurrency.intermediate.queue.blocking.blocking_queue;

//При обработке больших потоков данных через очереди становится явно недостаточно использования ConcurrentLinkedQueue.
// Если потоки, разгребающие очередь перестанут справляться с наплывом данных,
// то можно довольно быстро схлопотать out of memory или перегрузить IO/Net настолько,
// что производительность упадет в разы пока не настанет отказ системы по таймаутам
// или из за отсутствия свободных дескрипторов в системе.
// Для таких случаев нужна queue с возможностью задать размер очереди или с блокировками по условиям.
// Тут то и появляется интерфейс BlockingQueue, открывающий дорогу к целому набору полезных классов.
// Помимо возможности задавать размер queue, добавились новые методы, которые реагируют по-разному
// на незаполнение или переполнение queue.
// Так, например, при добавлении элемента в переполненную queue, один метод кинет IllegalStateException,
// другой вернет false, третий заблокирует поток, пока не появится место,
// четвертый же заблокирует поток с таймаутом и вернет false,
// если место так и не появится. Также стоит отметить, что блокирующие очереди не поддерживают null значения,
// т.к. это значение используется в методе poll как индикатор таймаута.

import ru.siksmfp.learn.concurrency.intermediate.queue.Del;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class BlockingQueueExample {
    public static void main(String[] args) {
        //  Класс блокирующей очереди, построенный на классическом кольцевом буфере.
        //  Помимо размера очереди, доступна возможность управлять «честностью» блокировок.
        //  Если fair=false (по умолчанию), то очередность работы потоков не гарантируется.
        //  Более подробно о «честности» можно посмотреть в описании ReentrantLock'a.
        BlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(10);

        // Класс, который позволяет вытаскивать элементы из очереди только по прошествии некоторой задержки,
        // определенной в каждом элементе через метод getDelay интерфейса Delayed
        BlockingQueue<Del> delayQueue = new DelayQueue<Del>();

        // Блокирующая очередь на связанных нодах, реализованная на «two lock queue» алгоритме:
        // один лок на добавление, другой на вытаскивание элемента.
        // За счет двух локов, по сравнению с ArrayBlockingQueue,
        // данный класс показывает более высокую производительность, но и расход памяти у него выше
        BlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>();

        // Является многопоточной оберткой над PriorityQueue.
        // При вставлении элемента в очередь, его порядок определяется в соответствии
        // с логикой Comparator'а или имплементации Comparable интерфейса у элементов.
        // Первым из очереди выходит самый наименьший элемент.
        BlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>();

        //Эта очередь работает по принципу один вошел, один вышел.
        // Каждая операция вставки блокирует «Producer» поток до тех пор,
        // пока «Consumer» поток не вытащит элемент из очереди и наоборот,
        // «Consumer» будет ждать пока «Producer» не вставит элемент.
        BlockingQueue<Integer> synchronousQueue = new SynchronousQueue<>();
    }
}