package cs2030s.fp;

/**
 * A container class the encapsulate a
 * lazily-evaluated-and-memoized value.
 * CS2030S Lab 8
 * AY22/23 Semester 1
 *
 * @author Dominic Ng (Group 16B)
 */

public class Memo<T> implements Immutatorable<T> {
  
  private Constant<? extends T> com;
  private Actually<T> val;
  
  private Memo(Actually<T> val, Constant<T> com) {
    this.com = com;
    this.val = val;
  }
  
  public static <T> Memo<T> from(T val) {
    return new Memo<T>(Actually.ok(val), null);
  }

  public static <T> Memo<T> from(Constant<T> com) {
    return new Memo<T>(Actually.err(), com);
  }
  
  public T get() {
    this.eval();
    return this.val.unless(null);
  }

  private void eval() {
    if (this.com != null) {
      this.val = Actually.<T>ok(this.com.init());
      this.com = null;
    }
  }
  
  @Override
  public <R> Memo<R> transform(Immutator<? extends R, ? super T> f) {
    return Memo.<R>from(() -> f.invoke(this.get()));
  }
  
  public <R> Memo<R> next(Immutator<? extends Memo<? extends R>, ? super T> f) {
    return Memo.<R>from(() -> f.invoke(this.get()).get());
  }
  
  public <S, R> Memo<R> combine(Memo<S> snd, Combiner<? extends R, ? super T, ? super S> f) {
    return Memo.<R>from(() -> f.combine(this.get(), snd.get()));
  }
  
  @Override
  public String toString() {
    if (this.com != null) {
      return "?";
    }
    return this.get().toString();
  }
  
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