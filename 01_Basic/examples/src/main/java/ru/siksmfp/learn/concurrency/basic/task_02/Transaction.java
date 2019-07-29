package ru.siksmfp.learn.concurrency.basic.task_02;

import ru.siksmfp.learn.concurrency.basic.Utils;

public class Transaction extends Thread {

    private TransactionType transactionType;
    private Account account;
    private double amount;

    public Transaction(Account account, TransactionType transactionType, double amount) {
        this.transactionType = transactionType;
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        switch (this.transactionType) {
            case DEPOSIT:
                depositAmount();
                printTransactionInfo();
                break;
            case WITHDRAW:
                withdrawAmount();
                printTransactionInfo();
                break;
            default:
                throw new IllegalStateException("Transactional is not valid");
        }
    }

    public void depositAmount() {
        account.depositAmount(amount);
    }

    public void withdrawAmount() {
        account.withdrawAmount(amount);
    }

    public void printTransactionInfo() {
        System.out.println("Transaction : TransactionType: " + transactionType + ", Amount: " + amount + " Account " + account.getNumber());
        int random = 1 + (int) (Math.random() * ((5 - 1) + 1));
        Utils.sleepSeconds(random);
    }
}
