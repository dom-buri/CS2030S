package cs2030s.fp;

/**
 * A container class the encapsulate a
 * lazily-evaluated-and-memoized value.
 *
 * @author Dominic Ng (Group 16B) 
 * @version CS2030S AY22/23 Semester 1
 */
public class Memo<T> implements Immutatorable<T> {
  
  private Constant<? extends T> com;
  private Actually<T> val;
  
  /**
   * Constructor for Memo.
   * 
   * @param val A T value which will be stored in the object.
   * @param com A constant which will be stored in the object.
   */
  private Memo(Actually<T> val, Constant<T> com) {
    this.com = com;
    this.val = val;
  }
  
  /**
   * Initializes the Memo object with the given value.
   * 
   * @param <T> A generic type T based on the value.
   * @param val A T value that will be used to create a new Memo object.
   * @return  A new initialized Memo object with the T value.
   */
  public static <T> Memo<T> from(T val) {
    return new Memo<T>(Actually.ok(val), null);
  }

  /**
   * Returns a new Memo object with a Constant that produce the value when needed.
   * 
   * @param <T> A generic type T based on the type in the Constant.
   * @param com A Constant that will produce the value when the .get() method is invoked.
   * @return A new Memo object with a Constant that produce the value when needed.
   */
  public static <T> Memo<T> from(Constant<T> com) {
    return new Memo<T>(Actually.err(), com);
  }
  
  /**
   * Calls the eval function and return the value.
   * 
   * @return The value that is evaluated by the Constant.
   */
  public T get() {
    this.eval();
    return this.val.unless(null);
  }
  
  /**
   * The logic that will evaluate the Constant and store the result in object's value.
   */
  private void eval() {
    if (this.com != null) {
      this.val = Actually.<T>ok(this.com.init());
      this.com = null;
    }
  }
  
  /**
   * The logic that invoke the invoke method from the Immutator and return a Memo object.
   * 
   * @param <R> A generic type R based on the type in the Immutator.
   * @param f An Immutator that will be used to invoke the value.
   * @return A new Memo object which is not yet initialized, with a 
   *         Constant that produce the value when needed.
   */
  @Override
  public <R> Memo<R> transform(Immutator<? extends R, ? super T> f) {
    return Memo.<R>from(() -> f.invoke(this.get()));
  }

  /**
   * The logic that invoke the invoke method from the Immutator that takes in a T object 
   * and return a Memo object.
   * 
   * @param <R> A generic type R based on the type in the Immutator.
   * @param f An Immutator that will be used to invoke the value.
   * @return A new Memo object which is not yet initialized, with a 
   *         Constant that produce the value when needed.
   */
  public <R> Memo<R> next(Immutator<? extends Memo<? extends R>, ? super T> f) {
    return Memo.<R>from(() -> f.invoke(this.get()).get());
  }

  /**
   * The logic that combines two Memo objects and return a new Memo object.
   * 
   * @param <S> A generic type S based on the type in the Combiner.
   * @param <R> A generic type R based on the type in the Combiner.
   * @param snd A Memo object that will be combined with this object.
   * @param f A Combiner object that will combine both Memo objects.
   * @return A new Memo object that has been combined.
   */
  public <S, R> Memo<R> combine(Memo<S> snd, Combiner<? extends R, ? super T, ? super S> f) {
    return Memo.<R>from(() -> f.combine(this.get(), snd.get()));
  }

  /**
   * The logic that combines two Memo objects and return a new Memo object.
   * 
   * @param pred An Immutator that will be used to invoke the value.
   * @return A new Memo object.
   */
  public Memo<Boolean> check(Immutator<Boolean, ? super T> pred) {
    return Memo.<Boolean>from(() -> pred.invoke(this.get()));
  }
  
  /**
  * Returns the string representation of the event.
  *
  * @return A string representing the event.
  */
  @Override
  public String toString() {
    if (this.com != null) {
      return "?";
    }
    return this.get().toString();
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
    if (!(obj instanceof Memo<?>)) {
      return false;
    }
    Memo<?> that = (Memo<?>) obj;
    that.eval();
    this.eval();
    return this.val.equals(that.val);
  }
}