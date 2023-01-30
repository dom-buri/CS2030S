package cs2030s.fp;

/**
 * Represent a container that can act on its content.
 * CS2030S Lab 8
 * AY22/23 Semester 1
 *
 * @author Dominic Ng (Group 16B)
 *
 * @param <T> The type of the content.
 */

interface Actionable<T> {
  /**
   * The method to act on its content.
   *
   * @param f The action.
   */
  void act(Action<? super T> f);
}