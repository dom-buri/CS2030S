/**
 * This class encapsulates an arrival event in the shop simulation. 
 * 
 * @author Dominic Ng (Group 14B) 
 * @version CS2030S AY22/23 Semester 1
 */

 class ArrivalEvent extends Event {
  private Customer customer;
  private Shop shop;

   /**
   * Constructor for arrival event.
   *
   * @param time        The time this event occurs.
   * @param customer    The customer associated with this event.
   * @param shop        The shop associated with this event.
   */
  public ArrivalEvent(double time, Customer customer, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

   /**
   * Returns the string representation of the event,
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    return String.format(super.toString() + ": %s arrives", this.customer);
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
    // Find the first available counter
    Counter counter = this.shop.getFirstAvailableCounter();
    if (counter == null) {
      return new Event[] {
	      new DepartureEvent(this.getTime(), this.customer)
	    };
    } else {
      // Else, the customer should go the the first 
      // available counter and get served.
      return new Event[] { 
        new ServiceBeginEvent(this.getTime(), this.customer, counter)
      };
    }
  }
}