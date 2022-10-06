/**
 * A non-generic Action to print the String
 * representation of the object.
 * 
 * <p>
 * CS2030S Lab 4
 * AY22/23 Semester 1
 * </p>
 *
 * @author Dominic Ng (Group 16B)
 */

class Print implements Action<Object> {
  @Override
  public void call(Object obj) {
    System.out.println(obj);
  }
}
