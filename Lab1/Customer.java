/**
 * @author Dominic Ng (Group 14B) 
 * @version CS2030S AY22/23 Semester 1
 */

 class Customer {
   /** 
   * The id of a customer
   * First customer has id 0. Next is 1, 2, etc. 
   */
  private int customerId;

  public Customer(int customerId) {
    this.customerId = customerId;
  }
  
  public int getCustomerId() {
    return this.customerId;
  }

  @Override
  public String toString() {
    return String.format("Customer %d", this.customerId);
  }
}