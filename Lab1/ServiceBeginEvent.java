/**
 * This class encapsulates a service begin event in the shop simulation. 
 * @author Dominic Ng (Group 14B) 
 * @version CS2030S AY22/23 Semester 1
 */

 class ServiceBeginEvent extends Event {
  private Customer customer;
  private double serviceTime;
  private Shop shop;
  private Counter counter;

    /**
   * Constructor for service begin event.
   *
   * @param time        The time this event occurs.
   * @param customer    The customer associated with this event.
   * @param serviceTime The time this customer takes for service.
   * @param counter     The counter associated with this event.
   * @param shop        The shop associated with this event.
   */
  public ServiceBeginEvent(double time, Customer customer, double serviceTime, Counter counter, Shop shop) {
    super(time);
    this.customer = customer;
    this.serviceTime = serviceTime;
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
    return String.format(super.toString() + ": Customer %d service begin (by Counter %d)", this.customer.getCustomerId(), this.counter.getCounterId());
    }

    /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() { 
      // The current event is a service-begin event.  
      // Mark the counter is unavailable, then schedule
      // a service-end event at the current time + service time.
      this.shop.getCounters()[this.counter.getCounterId()].setAvailabe(false);
      double endTime = this.getTime() + this.serviceTime;
      return new Event[] { 
        new ServiceEndEvent(endTime, this.customer, this.counter, this.shop)
     };
  }
}
