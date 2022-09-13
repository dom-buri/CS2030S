/**
 * This class encapsulates an counter queue event in the shop simulation.
 * 
 * @author Dominic Ng (Group 16B)
 * @version CS2030S AY22/23 Semester 1
 */

class CounterQueueEvent extends Event {
  private Customer customer;
  private Counter counter;
  
  /**
  * Constructor for Counter Queue event.
  *
  * @param time     The time this event occurs.
  * @param customer The customer associated with this event.
  * @param counter     The shop associated with this event.
  */
  public CounterQueueEvent(double time, Customer customer, Counter counter) {
    super(time);
    this.customer = customer;
    this.counter = counter;
  }
  
  /**
  * Returns the string representation of the event.
  *
  * @return A string representing the event.
  */
  @Override
  public String toString() {
    return String.format(super.toString() + ": %s joined counter queue (at %s)",
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
    this.counter.getCounterQueue().enq(customer);
    return new Event[] {};
  }
} 