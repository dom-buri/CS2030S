import cs2030s.fp.Combiner;
import cs2030s.fp.Immutator;
import cs2030s.fp.Memo;
import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper around a lazily evaluated and memoized
 * list that can be generated with a lambda expression.
 *
 * @author Dominic Ng (Group 16B) 
 * @version CS2030S AY22/23 Semester 1
 */
class MemoList<T> {
  /** The wrapped java.util.List object */
  private List<Memo<T>> list;

  /** 
   * A private constructor to initialize the list to the given one. 
   *
   * @param list The given java.util.List to wrap around.
   */
  private MemoList(List<Memo<T>> list) {
    this.list = list;
  }

  /** 
   * Generate the content of the list.  Given x and a lambda f, 
   * generate the list of n elements as [x, f(x), f(f(x)), f(f(f(x))), 
   * ... ]
   *
   * @param <T> The type of the elements in the list.
   * @param n The number of elements.
   * @param seed The first element.
   * @param f The immutator function on the elements.
   * @return The created list.
   */
  public static <T> MemoList<T> generate(int n, T seed, Immutator<? extends T, ? super T> f) {
    MemoList<T> memoList = new MemoList<>(new ArrayList<>());
    Memo<T> curr = Memo.from(seed);
    for (int i = 0; i < n; i++) {
      memoList.list.add(curr);
      curr = curr.transform(f);
    }
    return memoList;
  }

  /** 
   * Generate the content of the list.  Given x, y and a lambda f, 
   * generate the list of n elements as [x, y, f(x, y), f(y, (f(x, y)), f(f(x, y), f(y, f(x, y))),
   * ... ]
   *
   * @param <T> The type of the elements in the list.
   * @param n The number of elements.
   * @param fst The first element.
   * @param snd The second element.
   * @param c The combiner function on the elements.
   * @return The created list.
   */
  public static <T> MemoList<T> generate(int n, T fst, T snd,
                                         Combiner<? extends T, ? super T, ? super T> c) {
    MemoList<T> memoList = new MemoList<>(new ArrayList<>());

    Memo<T> curr = Memo.from(fst);
    Memo<T> next = Memo.from(snd);
    memoList.list.add(curr);

    for (int i = 0; i < n - 1; i++) {
      memoList.list.add(next);
      Memo<T> tmp = next;
      next = curr.combine(next, c);
      curr = tmp;
    }
    return memoList;
  }

  /** 
   * Map the content of the list using the immutator provided.
   *
   * @param <R> The return type of the elements in the list.
   * @param f The immutator function on the elements.
   * @return The mapped list.
   */
  public <R> MemoList<R> map(Immutator<? extends R, ? super T> f) {
    MemoList<R> memoList = new MemoList<>(new ArrayList<>());
    for (int i = 0; i < this.list.size(); i++) {
      memoList.list.add(this.list.get(i).transform(f));
    } 
    return memoList;
  }

  /** 
   * Map the content of the list using the immutator provided.
   *
   * @param <R> The return type of the elements in the list.
   * @param f The immutator function on the elements.
   * @return The flatmapped list.
   */
  public <R> MemoList<R> flatMap(Immutator<? extends MemoList<R>, ? super T> f) {
    MemoList<R> memoList = new MemoList<>(new ArrayList<>());
    MemoList<MemoList<R>> nestedList = this.map(f);

    for (int i = 0; i < nestedList.list.size(); i++) {
      memoList.list.addAll(nestedList.get(i).list);
    }
    
    return memoList;
  }

  /** 
   * Return the element at index i of the list.  
   *
   * @param i The index of the element to retrieved (0 for the 1st element).
   * @return The element at index i.
   */
  public T get(int i) {
    return this.list.get(i).get();
  }

  /** 
   * Find the index of a given element.
   *
   * @param v The value of the element to look for.
   * @return The index of the element in the list.  -1 is element is not in the list.
   */
  public int indexOf(T v) {
    return this.list.indexOf(Memo.from(v));
  }

  /** 
   * Return the string representation of the list.
   *
   * @return The string representation of the list.
   */
  @Override
  public String toString() {
    return this.list.toString();
  }
}
