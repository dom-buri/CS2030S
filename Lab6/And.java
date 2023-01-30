class And implements Cond {
  /** First Cond value. */
  private Cond lVal;

  /** Second Cond value. */
  private Cond rVal;

  /**
  * Constructor for And.
  *
  * @param lVal The first Cond object.
  * @param rVal The second Cond object.
  */
  public And(Cond lVal, Cond rVal) {
    this.lVal = lVal;
    this.rVal = rVal;
  }
  
  /**
  * The logic to evaluate 2 Cond objects.
  * 
  * @return First object evaluated and second object evaluated.
  */
  @Override
  public boolean eval() {
    return this.lVal.eval() && this.rVal.eval();
  }
  
  /**
  * Returns the string representation of the event.
  *
  * @return A string representing the event.
  */
  @Override
  public String toString() {
    return "(" + this.lVal + " & " + this.rVal + ")";
  }
  
  /**
  * Return the negation of the object.
  * 
  * @return Negation of the object.
  */
  @Override
  public Cond neg() {
    return new Or(lVal.neg(), rVal.neg());
  }
}