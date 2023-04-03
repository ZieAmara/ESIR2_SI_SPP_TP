package ESIR2_SI_SPP_TP.TP_0;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedVariableExample {
    private static long sharedVariable = 0;
    private static Lock lock = new ReentrantLock();

    static class IncrementThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                lock.lock();
                try {
                    sharedVariable++;
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class ReadThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                if (i % 20000 == 0) {
                    lock.lock();
                    try {
                        System.out.println("Thread " + getId() + ": " + sharedVariable);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // start's the time
        long startTime = System.currentTimeMillis();

        // Create 5 threads that increment the shared variable 
        for (int i = 0; i < 5; i++) {
            new IncrementThread().start();
        }

        // Create 15 threads that read and print the shared variable
        for (int i = 0; i < 15; i++) {
            new ReadThread().start();
        }

        // end's the time
        long endTime = System.currentTimeMillis();
        // print the total execution time
        long totalTime = endTime - startTime;
        System.out.println("Total execution time: " + totalTime + " ms");
    }
}
