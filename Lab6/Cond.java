interface Cond {
  /**
  * The logic to evaluate Cond objects.
  * 
  * @return A boolean that has been evaluated.
  */
  boolean eval();

  /**
  * Return the negation of the object.
  * 
  * @return Negation of the object.
  */
  Cond neg();
}