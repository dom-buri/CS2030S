/**
 * @author Dominic Ng (Group 14B)
 * @version CS2030S AY22/23 Semester 1
 */

class Shop {

  private Counter[] counters;
  private Queue queue;

  public Queue getQueue() {
    return this.queue;
  }

  public Shop(int numOfCounters, int queueLength) {
    counters = new Counter[numOfCounters];
    for (int i = 0; i < numOfCounters; i += 1) {
      counters[i] = new Counter();
    }
    this.queue = new Queue(queueLength);
  }

  public Counter getFirstAvailableCounter() {
    for (int i = 0; i < this.counters.length; i += 1) {
      if (this.counters[i].getAvailable()) {
        return this.counters[i];
      }
    }
    return null;
  }
}
