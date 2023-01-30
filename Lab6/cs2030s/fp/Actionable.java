package cs2030s.fp;

/**
 * Represent a container that can act on its content.
 * 
 * @author Dominic Ng (Group 16B) 
 * @version CS2030S AY22/23 Semester 1
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