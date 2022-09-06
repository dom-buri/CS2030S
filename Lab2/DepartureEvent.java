/**
 * This class encapsulates a departure event in the shop simulation.
 * 
 * @author Dominic Ng (Group 14B)
 * @version CS2030S AY22/23 Semester 1
 */

class DepartureEvent extends Event {
  private Customer customer;

  /**
   * Constructor for departure event.
   *
   * @param time     The time this event occurs.
   * @param customer The customer associated with this event.
   */
  public DepartureEvent(double time, Customer customer) {
    super(time);
    this.customer = customer;
  }

  /**
   * Returns the string representation of the event,
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    return String.format(super.toString() + ": %s departed", this.customer);
  }

  /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() {
    // Do Nothing
    return new Event[] {};
  }
}
