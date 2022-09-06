/**
 * This class encapsulates a queue event in the shop simulation.
 * 
 * @author Dominic Ng (Group 14B)
 * @version CS2030S AY22/23 Semester 1
 */

 class QueueEvent extends Event {
  private Customer customer;
  private Queue queue;

  public QueueEvent(double time, Customer customer, Queue queue) {
    super(time);
    this.customer = customer;
    this.queue = queue;
  }

  @Override
  public String toString() {
    return String.format(super.toString() + ": %s joined queue %s", this.customer, this.queue);
  }

  @Override
  public Event[] simulate() {
    queue.enq(customer);
    return new Event[] {};
  }
}
