package cs2030s.fp;

/**
 * This class encapsulates an Actually class.
 * 
 * @author Dominic Ng (Group 16B) 
 * @version CS2030S AY22/23 Semester 1
 * 
 * @param <T> The type of the content.
 */
public abstract class Actually<T> implements Immutatorable<T>, Actionable<T> {

  /**
  * The logic that invoke the invoke method from the Immutator and return an Actually object.
  *
  * @param <R> A generic type R based on the type in the Immutator.
  * @param f A Immutator that will be used to invoke the value.
  * @return A new Actually object.
  */
  public abstract <R> Actually<R> transform(Immutator<? extends R, ? super T> f);

  /**
   * The logic that retrieve the value from the Actually object.
   * 
   * @return The value in the Actually object.
   * @throws Exception Throws an error if it is a Failure object.
   */
  public abstract T unwrap() throws Exception;

  /**
   * The logic that invoke the init method from the Constant and return the value.
   * 
   * @param <U> A generic type U based on the type in the Constant.
   * @param c A Constant object that it's init method will be invoked.
   * @return The initialized value.
   */
  public abstract <U extends T> T except(Constant<? extends U> c);

  /**
   * The logic that invoke the call method from the Action.
   * 
   * @param a An Action that will be used to call the value.
   */
  public abstract void finish(Action<? super T> a);

  /**
   * The logic that either returns the object's value or the parameter's value.
   * 
   * @param <U> A generic type U based on the type of the object in the parameter.
   * @param u A U value that will be returned for the Failure objects.
   * @return Either the object's value or the U value.
   */
  public abstract <U extends T> T unless(U u);

  /**
   * The logic that invoke the invoke method from the Immutator and return an Actually object.
   * 
   * @param <U> A generic type U based on the type in the Immutator.
   * @param c An Immutator that will be used to invoke the value.
   * @return A new Actually object.
   */
  public abstract <U> Actually<U> next(Immutator<? extends Actually<? extends U>, ? super T> c);

  /**
  * The logic that return a new Success object.
  * @param <T> A generic type T based on the value.
  * @param res A T value for the constructor of the Success class.
  * @return A new Succes Object.
  */
  public static <T> Actually<T> ok(T res) {
    return new Success<T>(res);
  }
  
  /**
   * The logic that return a new Failure object.
   *   
   * @param <T> A generic type T based on the value.
   * @param exc An Exception for the constructor of the Failure class.
   * @return A new Failure Object.
   */
  public static <T> Actually<T> err(Exception exc) {
    @SuppressWarnings("unchecked")
    Actually<T> failure = (Actually<T>) new Failure(exc);
    return failure;
  }

  static class Success<T> extends Actually<T> {
    /** The value of the Success object. */
    private T value;

    /**
    * Constructor for Success.
    *
    * @param value The value encapsulated in the Success object
    */
    private Success(T value) {
      this.value = value;
    }

    /**
    * The logic that invoke the invoke method from the Immutator and return an Actually object.
    * 
    * @param f An Immutator that will be used to invoke the value.
    * @return A new Success object with the value inside transformed by the Immutator instance,
    *         or a new Failure object wrapping the exception. 
    */
    @Override
    public <U> Actually<U> next(Immutator<? extends Actually<? extends U>, ? super T> f) {
      try {
        @SuppressWarnings("unchecked")
        Actually<U> res = (Actually<U>) f.invoke(this.value);
        return res;
      } catch (Exception e) {
        return Actually.<U>err(e);
      }
    }

    /**
    * This method return the value in the object.
    * 
    * @return The value in the object.
    */
    @Override
    public T unwrap() {
      return this.value;
    }

    /**
    * This method return the value in the object.
    * 
    * @param c This parameter is not used.
    * @return The value in the object.
    */
    @Override
    public <U extends T> T except(Constant<? extends U> c) {
      return this.value;
    }

    /**
     * The logic that invoke the call method from the Action.
     * 
     * @param action An Action that will be used to call the value.
     */
    @Override
    public void finish(Action<? super T> action) {
      action.call(this.value);
    }

    /**
     * This method return the value in the object.
     * 
     * @param u This parameter is not used.
     * @return The value in the object.
     */
    @Override
    public <U extends T> T unless(U u) {
      return this.value;
    }

    /**
     * The logic that invoke the invoke method from the Immutator and return an Actually object.
     * 
     * @param f A Immutator that will be used to invoke the value.
     * @return A new Success object with the value inside transformed by the Immutator instance,
     *         or a new Failure object wrapping the exception. 
     */
    @Override
    public <R> Actually<R> transform(Immutator<? extends R, ? super T> f) {
      try {
        return Actually.ok(f.invoke(this.value));
      } catch (Exception e) {
        return Actually.err(e);
      }
    }

    /**
     * The logic that invoke the call method from the Action.
     * 
     * @param obj An Action that will be used to call the value.
     */
    @Override
    public void act(Action<? super T> obj) {
      obj.call(this.value);
    }

    /**
    * The logic that compares two objects, and returns true if the objects are equal.
    * and false if not,
    *
    * @param obj The object being compared to.
    * @return A boolean representing whether the two objects are equal.
    */
    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj instanceof Success) {
        Success<?> other = (Success<?>) obj;
        if (this.value == null || other.value == null) {
          return false;
        }
        return this.value.equals(other.value);
      }
      return false;
    }

    /**
    * Returns the string representation of the event.
    *
    * @return A string representing the event.
    */
    @Override
    public String toString() {
      return "<" + this.value + ">";
    }
  }

  static class Failure extends Actually<Object> {
    /** The exception of the Failure object. */
    private Exception exception;

    /**
    * Constructor for Failure.
    *
    * @param exception The exception encapsulated in the Failure Object.
    */
    private Failure(Exception exception) {
      this.exception = exception;
    }

    /**
     * The logic that propagate the exception contained as a new Failure.
     * 
     * @param c An Immutator that is not used.
     * @return A exception contained as a new Failure.
     */
    @Override
    public <U> Actually<U> next(Immutator<? extends Actually<? extends U>, ? super Object> c) {
      return Actually.err(this.exception);
    }

    /**
     * The logic that throws the exception stored in the class.
     * 
     * @return The value which is an exception.
     */
    @Override
    public Object unwrap() throws Exception {
      throw this.exception;
    }

    /**
     * The logic that invoke the init method from the Constant and return the value.
     * 
     * @param c A Constant that will be used to invoke the value.
     * @return A value that is a subtype of T from the result of invoking init from the Constant.
     */
    @Override
    public <U> U except(Constant<? extends U> c) {
      return c.init();
    }

    /**
     * This method does nothing.
     * 
     * @param action An Action that is not used.
     */
    @Override
    public void finish(Action<? super Object> action) {
    
    }

    /**
     * The logic that returns the value given in the parameter.
     * 
     * @param u The value that will be returned.
     * @return The value given in the parameter.
     */
    @Override
    public <U> U unless(U u) {
      return u;
    }

    /**
     * The logic that propagate the exception contained as a new Failure.
     * 
     * @param f An Immutator that is not used.
     * @return A exception contained as a new Failure.
     */
    @Override
    public <R> Actually<R> transform(Immutator<? extends R, ? super Object> f) {
      return Actually.err(this.exception);
    }


    /**
     * This method does nothing.
     * @param obj An Action that is not used.
     */
    @Override
    public void act(Action<? super Object> obj) {
  
    } 

    /**
    * The logic that compares two objects, and returns true if the objects are equal.
    * and false if not,
    *
    * @param obj The object being compared to.
    * @return A boolean representing whether the two objects are equal.
    */
    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj instanceof Failure) {
        Failure other = (Failure) obj;
        if (this.exception.getMessage() == null || other.exception.getMessage() == null) {
          return false;
        }
        return this.exception.getMessage().equals(other.exception.getMessage());
      }
      return false;
    }
    
    /**
    * Returns the string representation of the event.
    *
    * @return A string representing the event.
    */
    @Override
    public String toString() {
      return String.format("[%s] %s", this.exception.getClass().getName(),
                           this.exception.getMessage());
    }
  }
}