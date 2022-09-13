/**
 * This class is a Shop class which stores
 * the attributes and methods of Shop.
 * 
 * @author Dominic Ng (Group 16B)
 * @version CS2030S AY22/23 Semester 1
 */

class Shop {

  private Array<Counter> counters;
  private Queue<Customer> entranceQueue;

  public Queue<Customer> getQueue() {
    return this.entranceQueue;
  }

  public Shop(int numOfCounters, int queueLength, int counterQueueLength) {
    counters = new Array<Counter>(numOfCounters);
    for (int i = 0; i < numOfCounters; i += 1) {
      counters.set(i, new Counter(counterQueueLength));
    }
    this.entranceQueue = new Queue<>(queueLength);
  }

  public Counter getFirstAvailableCounter() {
    for (int i = 0; i < this.counters.getLength(); i += 1) {
      if (this.counters.get(i).getAvailable()) {
        return this.counters.get(i);
      }
    }
    return null;
  }

  public Counter getShortestCounterQueue() {
    Counter shortestCounter = this.counters.min();
    if (shortestCounter.getCounterQueue().isFull()) {
      return null;
    } else {
      return shortestCounter;
    }
  }

  @Override
  public String toString() {
    return this.entranceQueue.toString();
  }
}
