package ru.siksmfp.learn.concurrency.basic.task_02;

import ru.siksmfp.learn.concurrency.basic.Utils;

public class Main {
    public static void main(String[] args) {
        Account account = new Account("100500");

        // Total Expected Deposit: 10000 (100 x 100)
        for (int i = 0; i < 100; i++) {
            Transaction t = new Transaction(account, TransactionType.DEPOSIT, 100);
            t.start();
        }

        // Total Expected Withdrawal: 5000 (100 x 50)
        for (int i = 0; i < 100; i++) {
            Transaction t = new Transaction(account, TransactionType.WITHDRAW, 50);
            t.start();
        }

        Utils.sleepSeconds(4);

        System.out.println("Final Account Balance: " + account.getAccountBalance());
    }
}
