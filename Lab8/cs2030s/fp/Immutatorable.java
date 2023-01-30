package cs2030s.fp;

/**
 * Represent a container that can transforms its content to produce another container 
 * containing the immutated element, possible of different types.
 * CS2030S Lab 8
 * AY22/23 Semester 1
 *
 * @author Dominic Ng (Group 16B)
 *
 * @param <T> The type of the content.
 */

public interface Immutatorable<T> {
  /**
   * The method to produce another container with immutated element.
   *
   * @param <R> The type of the result value.
   * @param f The immutator.
   * @return A new container containing the immutated element.
   */
  <R> Immutatorable<R> transform(Immutator<? extends R, ? super T> f);
}