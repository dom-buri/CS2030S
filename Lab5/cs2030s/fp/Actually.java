package cs2030s.fp;

public abstract class Actually<T> implements Immutatorable<T>, Actionable<T> {

  public abstract <R> Actually<R> transform(Immutator<? extends R, ? super T> f);
  public abstract T unwrap() throws Exception;
  public abstract <U extends T> T except(Constant<? extends U> c);
  public abstract void finish(Action<? super T> a);
  public abstract <U extends T> T unless(U u);
  public abstract <U> Actually<U> next(Immutator<? extends Actually<? extends U>, ? super T> c);

  public static <T> Actually<T> ok(T res) {
    return new Success<T>(res);
  }
  
  public static <T> Actually<T> err(Exception exc) {
    @SuppressWarnings("unchecked")
    Actually<T> failure = (Actually<T>) new Failure(exc);
    return failure;
  }

  static class Success<T> extends Actually<T> {
    private T value;

    private Success(T value) {
      this.value = value;
    }

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

    @Override
    public T unwrap() {
      return this.value;
    }

    @Override
    public <U extends T> T except(Constant<? extends U> c) {
      return this.value;
    }

    @Override
    public void finish(Action<? super T> action) {
      action.call(this.value);
    }

    @Override
    public <U extends T> T unless(U u) {
      return this.value;
    }

    @Override
    public <R> Actually<R> transform(Immutator<? extends R, ? super T> f) {
      try {
        return Actually.ok(f.invoke(this.value));
      } catch (Exception e) {
        return Actually.err(e);
      }
    }

    @Override
    public void act(Action<? super T> obj) {
      obj.call(this.value);
    }

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

    @Override
    public String toString() {
      return "<" + this.value + ">";
    }
  }

  static class Failure extends Actually<Object> {
    private Exception exception;

    private Failure(Exception exception) {
      this.exception = exception;
    }

    @Override
    public <U> Actually<U> next(Immutator<? extends Actually<? extends U>, ? super Object> c) {
      return Actually.err(this.exception);
    }

    @Override
    public Object unwrap() throws Exception {
      throw this.exception;
    }

    @Override
    public <U> U except(Constant<? extends U> c) {
      return c.init();
    }

    @Override
    public void finish(Action<? super Object> action) {
    
    }

    @Override
    public <U> U unless(U u) {
      return u;
    }

    @Override
    public <R> Actually<R> transform(Immutator<? extends R, ? super Object> f) {
      return Actually.err(this.exception);
    }

    @Override
    public void act(Action<? super Object> obj) {
  
    } 

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
    
    @Override
    public String toString() {
      return String.format("[%s] %s", this.exception.getClass().getName(), this.exception.getMessage());
    }
  }
}