/**
 * The Applicable interface that can probably
 * transform if given something that is
 * probably an Immutator.
 * 
 * <p>
 * Contains a single abstract method apply.
 *
 * CS2030S Lab 4
 * AY22/23 Semester 1
 * </p>
 * 
 * @author Dominic Ng (Group 16B)
 */

interface Applicable<T> {
  <R> Probably<R> apply(Probably<? extends Immutator<? extends R, ? super T>> immutator);
}
