/**
 * The Action interface that can be called
 * on an object of type T to act.
 * 
 * <p>
 * Contains a single abstract method call.
 * 
 * CS2030S Lab 4
 * AY22/23 Semester 1
 * </p>
 * @author Dominic Ng (Group 16B)
 *
 */

interface Action<T> {
  void call(T t);
}
