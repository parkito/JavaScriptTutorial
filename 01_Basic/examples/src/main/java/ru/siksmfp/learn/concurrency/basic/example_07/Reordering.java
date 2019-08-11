package ru.siksmfp.learn.concurrency.basic.example_07;

public class Reordering {

    static class State {
        int a = 0;
        int b = 0;
        int c = 0;

        @Override
        public String toString() {
            return "State{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    '}';
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10_000_000; i++) {
            State state = new State();

            new Thread(() -> {
                state.a = 1;
                // a = 1, b = 0, c = 0
                state.b = 1;
                // a = 1, b = 1, c = 0
                state.c = state.a + 1;
                // a = 1, b = 1, c = 2
            }).start();

            new Thread(() -> {
                int tmpC = state.c;
                int tmpB = state.b;
                int tmpA = state.a;

                if (tmpB == 1 && tmpA == 0) {
                    System.out.println("Impossible state b == 1 && a == 0");
                    System.exit(1);
                }
                if (tmpC == 2 && tmpB == 0) {
                    System.out.println("Impossible state c == 2 && b == 0");
                    System.exit(1);
                }
                if (tmpC == 2 && tmpA == 0) {
                    System.out.println("Impossible state c == 2 && a == 0");
                    System.exit(1);
                }

                System.out.println(state);
            }).start();
        }

        System.out.println("done");
    }
}
