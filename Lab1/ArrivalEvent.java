/**
 * This class encapsulates an arrival event in the shop simulation. 
 * 
 * @author Dominic Ng (Group 14B) 
 * @version CS2030S AY22/23 Semester 1
 */

 class ArrivalEvent extends Event {
  private Customer customer;
  private double serviceTime;
  private Shop shop;

    /**
   * Constructor for arrival event.
   *
   * @param time        The time this event occurs.
   * @param customer    The customer associated with this event.
   * @param serviceTime The time this customer takes for service.
   * @param shop        The shop associated with this event.
   */
  public ArrivalEvent(double time, Customer customer, double serviceTime, Shop shop) {
    super(time);
    this.customer = customer;
    this.serviceTime = serviceTime;
    this.shop = shop;
  }

    /**
   * Returns the string representation of the event,
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    return String.format(super.toString() + ": Customer %d arrives", this.customer.getCustomerId());
  }

    /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() {
     // The current event is an arrival event.  
      // Find the first available counter.
      Counter counter = new Counter(-1);
      for (int i = 0; i < this.shop.getCounters().length; i += 1) {
        if (this.shop.getCounters()[i].getAvailable()) {
          counter = this.shop.getCounters()[i];
          break;
        }
      }
      if (counter.getCounterId() == -1) {
        // If no such counter can be found, the customer
        // should depart.
        return new Event[] { 
          new DepartureEvent(this.getTime(), this.customer)
        };
      } else {
        // Else, the customer should go the the first 
        // available counter and get served.
        return new Event[] { 
          new ServiceBeginEvent(this.getTime(), this.customer, 
              this.serviceTime, counter, this.shop)
      };
    }
  }
}