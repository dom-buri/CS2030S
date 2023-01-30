package cs2030s.fp;

/**
 * A container class the encapsulate
 * a value that may or may not be an error.
 * CS2030S Lab 8
 * AY22/23 Semester 1
 *
 * @author Dominic Ng (Group 16B)
 */

public class Actually<T> implements Immutatorable<T> {
  private T val;
  private Exception err;
  
  private Actually(T val, Exception err) {
    this.val = val;
    this.err = err;
  }
  
  public static <T> Actually<T> err() {
    // A common error for ease of use
    return new Actually<T>((T) null, new Exception("err"));
  }

  public static <T> Actually<T> err(Exception err) {
    return new Actually<T>((T) null, err);
  }

  public static <T> Actually<T> ok(T val) {
    return new Actually<T>(val, null);
  }
  
  public T except(Constant<? extends T> com) {
    return this.err == null ? this.val : com.init();
  }

  public <U extends T> T unless(U val) {
    return this.err == null ? this.val : val;
  }

  public void finish(Action<? super T> act) {
    if (this.err == null) {
      act.call(this.val);
    }
  }

  @Override
  public <R> Actually<R> transform(Immutator<? extends R, ? super T> f) {
    return this.err == null ? Actually.<R>ok(f.invoke(this.val)) : Actually.<R>err(this.err);
  }

  public <R> Actually<? extends R> next(Immutator<? extends Actually<? extends R>, ? super T> f) {
    if (this.err != null) {
      return Actually.err(this.err);
    } else {
      return f.invoke(this.val);
    }
  }

  public Actually<T> check(Immutator<Boolean, ? super T> pred) {
    if (this.err != null) {
      return Actually.err(this.err);
    }
    Boolean chk = pred.invoke(this.val);
    if (chk) {
      return this;
    }
    return Actually.err();
  }
  
  @Override
  public String toString() {
    if (this.err != null) {
      return "<>";
    }
    return "<" + this.val + ">";
  }
  
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
  
  public boolean hasValue() {
    return this.err == null;
  }
}