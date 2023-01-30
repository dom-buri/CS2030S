package cs2030s.fp;

/**
 * Represent a function that combines two values into one.  The two inputs
 * and the result can be of different types.
 * @author Dominic Ng (Group 16B) 
 * @version CS2030S AY22/23 Semester 1
 *
 * @param <R> The type of the return value
 * @param <S> The type of the first input value
 * @param <T> The type of the second input value
 */
@FunctionalInterface
public interface Combiner<R, S, T> {
  /**
   * The functional method to combines two values into one.
   *
   * @param s The first input value
   * @param t The second input value
   * @return The value after combining s and t.
   */
  R combine(S s, T t);
}