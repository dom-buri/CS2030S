package cs2030s.fp;

/**
 * Represent a function to initialise a constant value.
 * 
 * @author Dominic Ng (Group 16B) 
 * @version CS2030S AY22/23 Semester 1
 * 
 * @param <T> The type of the constant value.
 */
@FunctionalInterface
public interface Constant<T> {
  /**
   * The functional method to initialise the constant.
   *
   * @return The constant value.
   */
  T init();
}
