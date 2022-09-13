/**
 * This class encapsulates a service end event in the shop simulation.
 * 
 * @author Dominic Ng (Group 14B)
 * @version CS2030S AY22/23 Semester 1
 */

class ServiceEndEvent extends Event {
  private Customer customer;
  private Counter counter;
  private Shop shop;

  /**
   * Constructor for service end event.
   *
   * @param time     The time this event occurs.
   * @param customer The customer associated with this event.
   * @param counter  The counter associated with this event.
   * @param shop     The shop associated with this event.
   */
  public ServiceEndEvent(double time, Customer customer, Counter counter, Shop shop) {
    super(time);
    this.customer = customer;
    this.counter = counter;
    this.shop = shop;
  }

  /**
   * Returns the string representation of the event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    return String.format(super.toString() + ": %s service done (by %s)",
                         this.customer, this.counter);
  }

  /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() {
    // The current event is a service-end event.
    // Mark the counter is available, then schedule
    // a departure event at the current time.
    this.counter.makeAvailable();
    if (this.counter.getCounterQueue().isEmpty() && this.shop.getQueue().isEmpty()) {
      return new Event[] {
          new DepartureEvent(this.getTime(), this.customer)
      };
    } else if (!this.counter.getCounterQueue().isEmpty() && this.shop.getQueue().isEmpty()) {
      Customer nextCustomer = (Customer) this.counter.getCounterQueue().deq();
      return new Event[] {
          new DepartureEvent(this.getTime(), this.customer),
          new ServiceBeginEvent(this.getTime(), nextCustomer, this.counter, this.shop)
      };
    } else if (this.counter.getCounterQueue().isEmpty() && !this.shop.getQueue().isEmpty()) {
      Customer nextCustomer = (Customer) this.shop.getQueue().deq();
      return new Event[] {
        new DepartureEvent(this.getTime(), this.customer),
        new ServiceBeginEvent(this.getTime(), nextCustomer, this.counter, this.shop)
      };
    } else {
      Customer nextCustomer = (Customer) this.counter.getCounterQueue().deq();
      Customer entranceCustomer = (Customer) this.shop.getQueue().deq();
      return new Event[] {
        new DepartureEvent(this.getTime(), this.customer),
        new ServiceBeginEvent(this.getTime(), nextCustomer, this.counter, this.shop),
        new CounterQueueEvent(this.getTime(), entranceCustomer, this.counter)
      };
    }
  }
}
