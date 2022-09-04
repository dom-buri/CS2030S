/**
 * @author Dominic Ng (Group 14B) 
 * @version CS2030S AY22/23 Semester 1
 */

 class Customer {
   /** 
   * The id of a customer
   * First customer has id 0. Next is 1, 2, etc. 
   */
  private static int counter = 0;
  private int customerId;
  private double serviceTime;
  private double arrivalTime;

  public Customer(double arrivalTime, double serviceTime) {
    this.customerId = counter;
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
    counter++;
  }

  public double getEndTime() {
    return this.serviceTime + this.arrivalTime;
  }

  @Override
  public String toString() {
    return String.format("Customer %d", this.customerId);
  }
}