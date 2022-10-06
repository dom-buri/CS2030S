/**
 * A generic Immutator that takes in an object
 * that is T and returns an object that is probably T.
 *
 * <p>
 * CS2030S Lab 4
 * AY22/23 Semester 1
 * </p>
 *
 * @author Dominic Ng (Group 16B)
 */

class Improbable<T> implements Immutator<Probably<T>, T> {
  @Override
  public Probably<T> invoke(T t) {
    return Probably.just(t);
  }
}
