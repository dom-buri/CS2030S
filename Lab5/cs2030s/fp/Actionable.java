/**
* The Actionable interface that can
* act when given an action.
* 
* <p>
* Contains a single abstract method act.
* 
* CS2030S Lab 4
* AY22/23 Semester 1
* </p>
*
* @author Dominic Ng (Group 16B)
*/

package cs2030s.fp;

interface Actionable<T> {
  void act(Action<? super T> action);
}
