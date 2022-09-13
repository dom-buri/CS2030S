/**
* This class encapsulates a queue event in the shop simulation.
* 
* @author Dominic Ng (Group 16B)
* @version CS2030S AY22/23 Semester 1
*/

class EntranceQueueEvent extends Event {
  private Customer customer;
  private Shop shop;

  public EntranceQueueEvent(double time, Customer customer, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public String toString() {
    return String.format(super.toString() + ": %s joined shop queue %s", this.customer, this.shop);
  }

  @Override
  public Event[] simulate() {
    this.shop.getQueue().enq(customer);
    return new Event[] {};
  }
}
