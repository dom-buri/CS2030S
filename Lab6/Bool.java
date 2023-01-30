import cs2030s.fp.Constant;
import cs2030s.fp.Memo;

class Bool implements Cond {
  /** The Memo ojbect in Bool. */
  private Memo<Boolean> memo;
  
  /**
  * Constructor for Bool.
  *
  * @param val A Constant that produce the value when needed.
  */
  public Bool(Constant<Boolean> val) {
    this.memo = Memo.from(val);
  }
  
  /**
  * Return the initialized value of the Memo.
  * 
  * @return The initialized value of the Memo.
  */
  @Override
  public boolean eval() {
    return this.memo.get();
  }
  
  /**
  * Returns the string representation of the event.
  *
  * @return A string representing the event.
  */
  @Override
  public String toString() {
    return this.memo.toString().substring(0, 1);
  }
  
  /**
   * Return the negation of the object.
   * 
   * @return Negation of the object.
   */
  @Override
  public Cond neg() {
    return new Not(this);
  }
}
