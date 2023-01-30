package cs2030s.fp;

/**
 * A container class the encapsulate
 * a value that may or may not be an error.
 *
 * @author Dominic Ng (Group 16B) 
 * @version CS2030S AY22/23 Semester 1
 */
public class Actually<T> implements Immutatorable<T> {
  /** The value of the Actually object. */
  private T val;
  /** The exception of the Actually object. */
  private Exception err;
  
  /**
  * Constructor for Success.
  *
  * @param val The value encapsulated in the Actually object
  * @param err The exception encapsulated in the Actually object
  */
  private Actually(T val, Exception err) {
    this.val = val;
    this.err = err;
  }
  
  /**
   * The logic that return a new Actually object.
   *   
   * @param <T> A generic type T of the value in the object.
   * @return A new Actually Object.
   */
  public static <T> Actually<T> err() {
    // A common error for ease of use
    return new Actually<T>((T) null, new Exception("err"));
  }

  /**
   * The logic that return a new Actually object.
   *   
   * @param <T> A generic type T of the value in the object.
   * @param err An Exception for the constructor of the Failure class.
   * @return A new Actually Object.
   */
  public static <T> Actually<T> err(Exception err) {
    return new Actually<T>((T) null, err);
  }

  /**
  * The logic that return a new Actually object.
  * @param <T> A generic type T of the value in the object.
  * @param val A T value for the constructor of the Success class.
  * @return A new Actually Object.
  */
  public static <T> Actually<T> ok(T val) {
    return new Actually<T>(val, null);
  }
  
  /**
   * The logic that invoke the init method from the Constant and return the value.
   * 
   * @param com A Constant object that it's init method will be invoked.
   * @return The initialized value.
   */
  public T except(Constant<? extends T> com) {
    return this.err == null ? this.val : com.init();
  }

  /**
   * The logic that either returns the object's value or the parameter's value.
   * 
   * @param <U> A generic type U based on the type of the object in the parameter.
   * @param val A U value that will be returned for the Failure objects.
   * @return Either the object's value or the U value.
   */
  public <U extends T> T unless(U val) {
    return this.err == null ? this.val : val;
  }

  /**
   * The logic that invoke the call method from the Action.
   * 
   * @param act An Action that will be used to call the value.
   */
  public void finish(Action<? super T> act) {
    if (this.err == null) {
      act.call(this.val);
    }
  }

  /**
  * The logic that invoke the invoke method from the Immutator and return an Actually object.
  *
  * @param <R> A generic type R based on the type in the Immutator.
  * @param f A Immutator that will be used to invoke the value.
  * @return A new Actually object.
  */
  @Override
  public <R> Actually<R> transform(Immutator<? extends R, ? super T> f) {
    return this.err == null ? Actually.<R>ok(f.invoke(this.val)) : Actually.<R>err(this.err);
  }

  /**
   * The logic that invoke the invoke method from the Immutator and return an Actually object.
   * 
   * @param <R> A generic type R based on the type in the Immutator.
   * @param f An Immutator that will be used to invoke the value.
   * @return A new Actually object.
   */
  public <R> Actually<? extends R> next(Immutator<? extends Actually<? extends R>, ? super T> f) {
    if (this.err != null) {
      return Actually.err(this.err);
    } else {
      return f.invoke(this.val);
    }
  }

  /**
   * The logic that combines two Memo objects and return a new Memo object.
   * 
   * @param pred An Immutator that will be used to invoke the value.
   * @return A new Actually object.
   */
  public Actually<T> check(Immutator<Boolean, ? super T> pred) {
    if (this.err != null) {
      return this;
    }
    Boolean chk = pred.invoke(this.val);
    if (chk) {
      return this;
    }
    return Actually.err();
  }
  
  /**
  * Returns the string representation of the event.
  *
  * @return A string representing the event.
  */
  @Override
  public String toString() {
    if (this.err != null) {
      return "<>";
    }
    return "<" + this.val + ">";
  }
  
  /**
  * The logic to check if the two objects are equal.
  *
  * @param obj A object to be checked against.
  * @return A boolean representing whether both objects are equal.
  */
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Actually<?>)) {
      return false;
    }
    Actually<?> that = (Actually<?>) obj;
    if (this.err != null) {
      // for simplicity, all err is the same
      return that.err != null;
    }
    if (this.val == null) {
      return that.val == null;
    }
    return this.val.equals(that.val);
  }
}