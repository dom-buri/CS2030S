/**
 * The Generic Array class for CS2030S.
 *
 * @author Dominic Ng (16B)
 * @version CS2030S AY21/22 Semester 2
 */
class Array<T extends Comparable<T>> { 
  private T[] array;
  
  // The only way we can put an object into array is through the method set() and we
  // only put object of type T inside. So it is safe to cast `Object[]` to `T[]`.
  public Array(int size) {
    @SuppressWarnings({"unchecked", "rawtypes"})
    T[] temp = (T[]) new Comparable[size];
    this.array = temp;
  }

  public void set(int index, T item) {
    this.array[index] = item;
  }

  public T get(int index) {
    return this.array[index];
  }

  public T min() {
    int index = 0;
    for (int i = 0; i < this.array.length; i++) {
      if (this.array[index].compareTo(this.array[i]) == 1) {
        index = i;
      }
    }
    return this.array[index];
  }

  public int getLength() {
    return this.array.length;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}