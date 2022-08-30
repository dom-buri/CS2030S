/**
 * This class encapsulates a service end event in the shop simulation. 
 * @author Dominic Ng (Group 14B) 
 * @version CS2030S AY22/23 Semester 1
 */

 class ServiceEndEvent extends Event {
  private Customer customer;
  private Shop shop;
  private Counter counter;

    /**
   * Constructor for service end event.
   *
   * @param time        The time this event occurs.
   * @param customer    The customer associated with this event.
   * @param counter     The counter associated with this event.
   * @param shop        The shop associated with this event.
   */
  public ServiceEndEvent(double time, Customer customer, Counter counter, Shop shop) {
    super(time);
    this.customer = customer;
    this.counter = counter;
    this.shop = shop;
  }

   /**
   * Returns the string representation of the event,
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    return String.format(super.toString() + ": Customer %d service done (by Counter %d)", this.customer.getCustomerId(), this.counter.getCounterId());
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
      this.shop.getCounters()[this.counter.getCounterId()].setAvailabe(true);
      return new Event[] { 
        new DepartureEvent(this.getTime(), this.customer)
      };
  }
}
