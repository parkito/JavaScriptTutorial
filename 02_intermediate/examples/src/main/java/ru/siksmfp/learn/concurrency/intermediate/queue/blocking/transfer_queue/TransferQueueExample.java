package ru.siksmfp.learn.concurrency.intermediate.queue.blocking.transfer_queue;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

// Данный интерфейс может быть интересен тем, что при добавлении элемента в очередь
// существует возможность заблокировать вставляющий «Producer» поток до тех пор,
// пока другой поток «Consumer» не вытащит элемент из очереди.
// Блокировка может быть как с таймаутом, так и вовсе может быть заменена проверкой
// на наличие ожидающих «Consumer»ов.
// Тем самым появляется возможность реализации механизма
// передачи сообщений с поддержкой как синхронных, так и асинхронных сообщений.
public class TransferQueueExample {

    public static void main(String[] args) {

        // Реализация TransferQueue на основе алгоритма Dual Queues with Slack.
        // Активно использует CAS и парковку потоков, когда они находятся в режиме ожидания.
        TransferQueue<Integer> linkedTransferQueue =new LinkedTransferQueue<>();

        //PS Если ты юзаешь такую экзотику, должно  быть ты в отчаянии.
        //Шучу. Не от хорошей это жизни.
    }
}
