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

  public Customer(double serviceTime) {
    this.customerId = counter;
    this.serviceTime = serviceTime;
    counter++;
  }

  public double calculateEndTime(double startTime) {
    return this.serviceTime + startTime;
  }

  @Override
  public String toString() {
    return String.format("C%d", this.customerId);
  }
}
