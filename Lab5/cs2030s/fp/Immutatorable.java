/**
 * The Immutatorable interface that can
 * transform when given something that is
 * Immutator.
 *
 * <p>
 * Contains a single abstract method transform.
 *
 * CS2030S Lab 4
 * AY22/23 Semester 1
 * </p>
 *
 * @author Dominic Ng (Group 16B)
 */

package cs2030s.fp;

public interface Immutatorable<T> {
  <R> Immutatorable<? super R> transform(Immutator<? extends R, ? super T> immutator);
}
