package cs2030s.fp;

/**
 * This class encapsulates a Lazy class.
 * 
 * @author Dominic Ng (Group 16B) 
 * @version CS2030S AY22/23 Semester 1
 * 
 * @param <T> The type of the content.
 */
public class Lazy<T> implements Immutatorable<T> {
  /** The value of the Lazy object. */
  private Constant<? extends T> init;

  /**
   * Constructor for Lazy.
   * 
   * @param c A Constant that produce the value when needed.
   */
  protected Lazy(Constant<? extends T> c) {
    this.init = c;
  }

  /**
   * Initializes the Lazy object with the given value.
   * 
   * @param <T> A generic type T based on the value.
   * @param v A T value that will be used to create a new Lazy object.
   * @return A new initialized Lazy object with the T value.
   */
  public static <T> Lazy<T> from(T v) {
    return new Lazy<T>(() -> v);
  }

  /**
   * Returns a new Lazy object with a Constant that produce the value when needed.
   * 
   * @param <T> A generic type R based on the type in the Constant.
   * @param c A Constant that will produce the value when the .get() method is invoked.
   * @return A new Lazy object with a Constant that produce the value when needed.
   */
  public static <T> Lazy<T> from(Constant<? extends T> c) {
    return new Lazy<T>(c);
  }

  /**
   * Return the value of the Constant::init.
   * 
   * @return The value that is returned by the Constant::init.
   */
  public T get() {
    return this.init.init();
  }

  /**
   * Getter method for the init field.
   * 
   * @return The Constant field of the Lazy object.
   */
  protected Constant<? extends T> getInit() {
    return this.init;
  }

  /** 
   * The logic that invoke the invoke method from the Immutator and return a Lazy object.
   * 
   * @param <R> A generic type R based on the type in the Immutator.
   * @param f An Immutator that will be used to invoke the value.
   * @return A new Lazy object with a Constant that produce the value when needed.
   */
  @Override
  public <R> Lazy<R> transform(Immutator<? extends R, ? super T> f) {
    return new Lazy<R>(() -> f.invoke(this.get()));
  }
  
  /**
   * The logic that invoke the invoke method from the Immutator that takes in a T object 
   * and return a Lazy object.
   * 
   * @param <U> A generic type U based on the type in the Immutator.
   * @param f An Immutator that will be used to invoke the value.
   * @return A new Lazy object with a Constant that produce the value when needed.
   */
  public <U> Lazy<U> next(Immutator<? extends Lazy<? extends U>, ? super T> f) {
    return new Lazy<U>(() -> f.invoke(this.get()).get());
  }

  /**
  * Returns the string representation of the event.
  *
  * @return A string representing the event.
  */
  @Override
  public String toString() {
    return String.valueOf(this.get());
  }
}
