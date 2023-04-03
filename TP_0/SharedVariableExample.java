public class SharedVariableExample {
    private static long sharedVariable = 0;

    static class IncrementThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                sharedVariable++;
            }
        }
    }

    static class ReadThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                if (i % 20000 == 0) {
                    System.out.println("Thread " + getId() + ": " + sharedVariable);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Create 5 threads that increment the shared variable 
        for (int i = 0; i < 5; i++) {
            new IncrementThread().start();
        }

        // Create 15 threads that read and print the shared variable
        for (int i = 0; i < 15; i++) {
            new ReadThread().start();
        }
    }
}
