package cs2030s.fp;

/**
 * Represent a function that immutate one value into another, possible of different types.
 * @author Dominic Ng (Group 16B) 
 * @version CS2030S AY22/23 Semester 1
 *
 * @param <R> The type of the result value.
 * @param <P> The type of the input value.
 */
@FunctionalInterface
public interface Immutator<R, P> {
  /**
   * The functional method to immutate the value p.
   *
   * @param p The input value.
   * @return The value after applying the given immutation on p.
   */
  R invoke(P p);
}