class Not implements Cond {
  /** The Cond value in Not. */
  private Cond val;
  
  /**
  * Constructor for Not.
  *
  * @param val A Cond object to be stored in the class.
  */
  public Not(Cond val) {
    this.val = val;
  }
  
  /**
  * The logic to evaluate this object.
  * 
  * @return The negation of this object.
  */
  @Override
  public boolean eval() {
    return !this.val.eval();
  }

  /**
  * Returns the string representation of the event.
  *
  * @return A string representing the event.
  */
  @Override
  public String toString() {
    return "!(" + this.val + ")";
  }
  
  /**
  * Return this object.
  * 
  * @return This object.
  */
  @Override
  public Cond neg() {
    return this.val;
  }
}