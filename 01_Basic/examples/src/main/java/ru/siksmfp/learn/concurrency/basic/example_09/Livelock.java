package ru.siksmfp.learn.concurrency.basic.example_09;

import ru.siksmfp.learn.concurrency.basic.Utils;

public class Livelock {

    private static class Criminal {
        private boolean hostageReleased = false;

        public void releaseHostage(Police police) {
            while (!police.isMoneySent()) {
                System.out.println("Criminal: waiting police to give ransom");
                Utils.sleepSeconds(1);
            }

            System.out.println("Criminal: released hostage");
            hostageReleased = true;
        }

        public boolean isHostageReleased() {
            return hostageReleased;
        }
    }

    private static class Police {
        private boolean moneySent = false;

        public void giveRansom(Criminal criminal) {
            while (!criminal.isHostageReleased()) {
                System.out.println("Police: waiting criminal to release hostage");
                Utils.sleepSeconds(1);
            }

            System.out.println("Police: sent money");
            moneySent = true;
        }

        public boolean isMoneySent() {
            return moneySent;
        }
    }

    public static void main(String[] args) {
        Police police = new Police();
        Criminal criminal = new Criminal();

        new Thread(() -> police.giveRansom(criminal)).start();
        new Thread(() -> criminal.releaseHostage(police)).start();
    }
}
