package ESIR2_SI_SPP_TP.TP_0;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedVariableExample {
    private static long sharedVariable = 0;
    // Reentrant lock
    private static Lock lock_1 = new ReentrantLock();
    
    // ReentrantReadWriteLock
    private static ReentrantReadWriteLock lock_2 = new ReentrantReadWriteLock();


    static class IncrementThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i <= 100000; i++) {
                // lock_1.lock();
                lock_2.writeLock().lock();
                try {
                    sharedVariable++;
                } finally {
                    // lock_1.unlock();
                    lock_2.writeLock().unlock();
                }
            }
        }
    }

    static class ReadThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i <= 100000; i++) {
                if (i % 20000 == 0) {
                    // lock_1.lock();
                    lock_2.readLock().lock();
                    try {
                        System.out.println("Thread " + i + ": " + sharedVariable);
                    } finally {
                        // lock_1.unlock();
                        lock_2.readLock().unlock();
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
        System.out.println("\t\t\tTotal execution time: " + totalTime + " ms");
    }
}
