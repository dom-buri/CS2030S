package cs2030s.fp;

public abstract class Transformer<R, P> implements Immutator<R, P> {
  private Transformer<R, P> fst;
  private Transformer<R, P> scd;
  
  public Transformer<R, P> after(Transformer<R, P> other) {
    this.fst = other;
    this.scd = this;
    return this;
  }
  
  public Transformer<R, P> before(Transformer<R, P> other) {
    this.fst = this;
    this.scd = other;
    return this;
  }
  
  @Override
  public R invoke(P p) {
    return this.fst.invoke(p);
  }
}
