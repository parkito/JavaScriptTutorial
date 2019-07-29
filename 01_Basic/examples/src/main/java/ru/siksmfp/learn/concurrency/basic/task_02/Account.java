package ru.siksmfp.learn.concurrency.basic.task_02;

public class Account {
    private String number;
    private double balance;

    public Account(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public double getAccountBalance() {
        return balance;
    }

    public void depositAmount(double amount) {
        System.out.println("Account " + number + " depositing up to " + amount);
        balance = balance + amount;
    }

    public void withdrawAmount(double amount) {
        System.out.println("Account " + number + " withdrawing down to " + amount);
        if (amount > balance) {
            throw new IllegalStateException("There is not enough money on balance");
        } else {
            balance = balance - amount;
        }
    }
}
