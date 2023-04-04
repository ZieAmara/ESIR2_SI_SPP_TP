public class MonitorSemaphore implements SemaphoreInterface {
          private int count;
      
          public MonitorSemaphore() {
              count = 0;
          }
      
          public synchronized void up() {
              count++;
              notify();
          }
      
          public synchronized void down() {
              while (count == 0) {
                  try {
                      wait();
                  } catch (InterruptedException e) {
                      // Do nothing
                  }
              }
              count--;
          }
      
          public synchronized int releaseAll() {
              int numUnblocked = 0;
              while (count > 0) {
                  count--;
                  numUnblocked++;
                  notify();
              }
              return numUnblocked;
          }
      }
      