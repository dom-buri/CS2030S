/**
 * The Immutator interface that can transform 
 * to type T2, an object of type T1.
 * 
 * <p>
 * Contains a single abstract method invoke.
 * 
 * CS2030S Lab 4
 * AY22/23 Semester 1
 * </p>
 * 
 * @author Dominic Ng (Group 16B)
 *
 */

interface Immutator<R, P> {
  R invoke(P p);
}
