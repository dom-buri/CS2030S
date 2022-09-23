/**
 * This class is a Counter class which stores
 * the attributes and methods of Counter.
 * 
 * @author Dominic Ng (Group 16B)
 * @version CS2030S AY22/23 Semester 1
 */

class Counter implements Comparable<Counter> {
  private static int count = 0;
  private int counterId;
  private boolean available;
  private Queue<Customer> counterQueue;

  public Counter(int queueLength) {
    this.counterId = count;
    this.available = true;
    this.counterQueue = new Queue<>(queueLength);
    count++;
  }

  public boolean getAvailable() {
    return this.available;
  }

  public Queue<Customer> getCounterQueue() {
    return this.counterQueue;
  }

  public void makeAvailable() {
    this.available = true;
  }

  public void makeUnavailable() {
    this.available = false;
  }

  @Override
  public int compareTo(Counter other) {
    if (this.available && other.getAvailable()) {
      if (this.counterId < other.counterId) {
        return -1;
      } else {
        return 1;
      }
    } else if (this.available) {
      return -1;
    } else if (other.getAvailable()) {
      return 1;
    } else {
      if (this.counterQueue.isFull() && other.getCounterQueue().isFull()) {
        return -1;
      } else {
        if (this.counterQueue.length() == other.getCounterQueue().length()) {
          if (this.counterId < other.counterId) {
            return -1;
          } else {
            return 1;
          }
        } else if (this.counterQueue.length() > other.getCounterQueue().length()) {
          return 1;
        } else {
          return -1;
        }
      }
    }
  }

  @Override
  public String toString() {
    return String.format("S%d %s", this.counterId, this.counterQueue);
  }
}
