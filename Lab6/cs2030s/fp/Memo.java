package cs2030s.fp;

/**
 * This class encapsulates a Memo class.
 * 
 * @author Dominic Ng (Group 16B) 
 * @version CS2030S AY22/23 Semester 1
 * 
 * @param <T> The type of the content.
 */
public class Memo<T> extends Lazy<T> {
  /** The value of the Memo object. */
  private Actually<T> value;

  /**
   * Constructor for Memo.
   * 
   * @param v A T value which will be stored in the object.
   */
  private Memo(T v) {
    super(null);
    this.value = Actually.ok(v);
  }

  /**
   * Constructor for Memo.
   * 
   * @param c A Constant that produce the value when needed.
   */
  private Memo(Constant<? extends T> c) {
    super(c);
    this.value = Actually.err(new Exception("Not Initialized"));
  }

  /**
   * Initializes the Memo object with the given value.
   * 
   * @param <T> A generic type T based on the value.
   * @param v A T value that will be used to create a new Memo object.
   * @return  A new initialized Memo object with the T value.
   */
  public static <T> Memo<T> from(T v) {
    return new Memo<T>(v);
  }

  /**
   * Returns a new Memo object with a Constant that produce the value when needed.
   * 
   * @param <T> A generic type T based on the type in the Constant.
   * @param c A Constant that will produce the value when the .get() method is invoked.
   * @return A new Memo object with a Constant that produce the value when needed.
   */
  public static <T> Memo<T> from(Constant<? extends T> c) {
    return new Memo<T>(c);
  }

  /**
   * The logic that will evaluate the Constant and store the result in object's value.
   * 
   * @return The value that is evaluated by the Constant.
   */
  @Override
  public T get() {
    T res = this.value.except(super.getInit());
    this.value = Actually.ok(res);
    return res;
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
    return new Memo<R>(() -> f.invoke(this.get()));
  }

  /**
   * The logic that invoke the invoke method from the Immutator that takes in a T object 
   * and return a Memo object.
   * 
   * @param <U> A generic type U based on the type in the Immutator.
   * @param f An Immutator that will be used to invoke the value.
   * @return A new Memo object which is not yet initialized, with a 
   *         Constant that produce the value when needed.
   */
  @Override
  public <U> Memo<U> next(Immutator<? extends Lazy<? extends U>, ? super T> f) {
    return new Memo<U>(() -> f.invoke(this.get()).get());
  }

  /**
   * The logic that combines two Memo objects and return a new Memo object.
   * 
   * @param <S> A generic type S based on the type in the Combiner.
   * @param <R> A generic type R based on the type in the Combiner.
   * @param memo A Memo object that will be combined with this object.
   * @param combiner A Combiner object that will combine both Memo objects.
   * @return A new Memo object that has been combined.
   */
  public <S, R> Memo<R> combine(Memo<S> memo, Combiner<R, T, S> combiner) {
    return new Memo<R>(() -> combiner.combine(this.get(), memo.get()));
  }
  
  /**
  * Returns the string representation of the event.
  *
  * @return A string representing the event.
  */
  @Override
  public String toString() {
    return this.value.transform(v -> String.valueOf(v)).unless("?");
  }
}
