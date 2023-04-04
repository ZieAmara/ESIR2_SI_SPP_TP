public class ExtendedSemaphore implements SemaphoreInterface {
    private int permits;
    private int waitingThreads;
    private final Object lock = new Object();

    public ExtendedSemaphore() {
        this.permits = 0;
        this.waitingThreads = 0;
    }

    public void up() {
        synchronized (lock) {
            permits++;
            if (waitingThreads > 0) {
                lock.notify();
            }
        }
    }

    public void down() {
        synchronized (lock) {
            while (permits == 0) {
                waitingThreads++;
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waitingThreads--;
            }
            permits--;
        }
    }

    public int releaseAll() {
        synchronized (lock) {
            int releasedThreads = waitingThreads;
            waitingThreads = 0;
            permits = 0;
            lock.notifyAll();
            return releasedThreads;
        }
    }
}
