public class SemImpl2 implements SemaphoreInterface {
    private int permits;
    private int waitingThreads;

    public SemImpl2() {
        this.permits = 0;
        this.waitingThreads = 0;
    }

    public synchronized void up() {
            permits++;
            if (waitingThreads > 0) {
                notify();
            }
        
    }

    public synchronized void down() {
        while (permits == 0) {
            waitingThreads++;
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitingThreads--;
        }
        permits--;
    }

    public synchronized int releaseAll() {
        try {
            int releasedThreads = waitingThreads;
            waitingThreads = 0;
            permits = 0;
            notifyAll();
            return releasedThreads;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return waitingThreads;
    }
    
    //public static void main(String []args){
    //    
    //}
}