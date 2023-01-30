package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;

/**
 * A Infinite List class the encapsulate a
 * lazily-evaluated-and-memoized head and tail.
 * CS2030S Lab 8
 * AY22/23 Semester 1
 *
 * @author Dominic Ng (Group 16B)
 */

public class InfiniteList<T> {
  /** The element of head. */
  private Memo<Actually<T>> head;
  /** The tail of the InfiniteList. */
  private Memo<InfiniteList<T>> tail;
  /** The single instance of the End class. */
  private static final InfiniteList<?> END = new End();

  /** 
  * A private constructor to initialize the infinite list by providing the head and tail. 
  *
  * @param head The given Actually object wrapped in Memo.
  * @param tail The given InfiniteList wrapped in Memo.
  */
  private InfiniteList(Memo<Actually<T>> head, Memo<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  /** 
  * Generate the content of the list. Given a producer prod, 
  * generate the list of infinite elements by lazily initializing the prod
  * and memoize the result.
  *
  * @param <T> The type of the elements in the infinite list.
  * @param prod The constant function used to generate the elements.
  * @return The generated infinite list.
  */
  public static <T> InfiniteList<T> generate(Constant<T> prod) {
    return new InfiniteList<T>(
      Memo.from(() -> Actually.ok(prod.init())),
      Memo.from(() -> InfiniteList.generate(prod))
    );
  }

  /** 
  * Generate the content of the list.  Given seed and a lambda func, 
  * generate the list of infinite elements as [x, func(x), func(func(x))
  * ... ]
  *
  * @param <T> The type of the elements in the list.
  * @param seed The first element.
  * @param func The immutator function on the elements.
  * @return The generated infinite list.
  */
  public static <T> InfiniteList<T> iterate(T seed, Immutator<T, T> func) {
    return new InfiniteList<T>(
      Memo.from(Actually.ok(seed)),
      Memo.from(() -> InfiniteList.iterate(func.invoke(seed), func))
    );
  }
  
  /** 
  * Return the element in the head of the infinite list.  
  *
  * @return The element in the head of the infinite list if it exists, and return the
  *         element in the tail's head if the element in the current infinite list's 
  *         head does not exist.
  */
  public T head() {
    return this.head.get().except(() -> this.tail.get().head());
  }
  
  /** 
  * Return the tail of type InfiniteList of the current InfiniteList. 
  *
  * @return The tail of type InfiniteList if the head of the current infinite list exists, and
  *         return the tail's tail if the head of the current infinite list does not exists.
  */
  public InfiniteList<T> tail() {
    return this.head.get().hasValue() ? this.tail.get() : this.tail.get().tail();
  }
  
  /** 
  * Map the elements in the head and the tail of the infinite list using the immutator provided.
  *
  * @param <R> The return type of the elements in the infinite list.
  * @param f The immutator function on the elements in the head and tail.
  * @return A infinite list where the elements in the head and tail has been mapped.
  */
  public <R> InfiniteList<R> map(Immutator<? extends R, ? super T> f) {
    return new InfiniteList<R>(
      Memo.from(() -> this.head.get().transform(f)),
      Memo.from(() -> this.tail.get().map(f))
    );
  }
  
  /** 
  * Filter the elements in the head and the tail of the infinite list
  * using the predicate immutator provided.
  *
  * @param pred The predicate immutator function on the elements in the head and tail.
  * @return A infinite list where the elements in the head and tail has been filtered.
  */
  public InfiniteList<T> filter(Immutator<Boolean, ? super T> pred) {
    return new InfiniteList<T>(
      Memo.from(() -> this.head.get().check(pred)),
      Memo.from(() -> this.tail.get().filter(pred))
    );
  }
  
  /** 
  * Truncate the InfiniteList to a finite list with at most n elements.
  *
  * @param n The number of elements in the newly created finite list.
  * @return A Truncated list where the number of elements is equal to n.
  */
  public InfiniteList<T> limit(long n) {
    if (n < 1) {
      return InfiniteList.end();
    } else if (n == 1) {
      return new InfiniteList<T>(
        this.head,
        Memo.from(() -> this.head.get().hasValue() ? InfiniteList.end() : this.tail.get().limit(n))
      );
    } else {
      return new InfiniteList<T>(
        this.head,
        this.tail.transform(x -> this.head.get().hasValue() ? x.limit(n - 1) : x.limit(n))
      );
    }
  }

  /** 
  * Truncate the InfinteList as soon as it finds an element that evaluates the predicate to false.
  *
  * @param pred The predicate immutator function on the elements in the head and tail.
  * @return A infinite list where the elements in the head and tail has been truncated.
  */
  public InfiniteList<T> takeWhile(Immutator<Boolean, ? super T> pred) {
    Memo<Actually<T>> filteredMemo = Memo.from(() -> this.head.get().check(pred));
    return new InfiniteList<T>(
      filteredMemo,
      Memo.from(() -> this.head.get().hasValue() && !filteredMemo.get().hasValue() ?
                      InfiniteList.end() : this.tail.get().takeWhile(pred))
    );
  }
  
  /** 
  * A terminal method that collects the elements in the InfinteList into a java.util.List.
  *
  * @return A java.util.List where the elements in the head and tail has been collected.
  */
  public List<T> toList() {
    List<T> list = new ArrayList<>();
    this.head.get().finish(x -> list.add(x));
    list.addAll(this.tail.get().toList());
    return list;
  }

  /** 
  * Performs a reduction on the elements of this stream, using the provided identity value and
  * an associative accumulation function, and returns a value describing the reduced value, if any.
  *
  * @param <U> The type of the identity.
  * @param id The identity that the accumulator call the combine method on.
  * @param acc The combiner function that combines the identity and the element of the 
  *            InfiniteList.
  * @return The reduced value of the InfiniteList after performing the accumlation method.
  */
  public <U> U reduce(U id, Combiner<U, U, ? super T> acc) {
    return this.tail.get().reduce(this.head.get().transform(
                                  x -> acc.combine(id, x)).unless(id), acc);
  }

  /** 
  * A terminal method that counts the number of elements in the InfinteList.
  *
  * @return The number of elements in the InfiniteList.
  */
  public long count() {
    return this.reduce(0L, (x, y) -> x + 1L);
  }

  /** 
  * Return the string representation of the infinite list.
  *
  * @return The string representation of the infinite list.
  */
  @Override
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }

  /**
  * Return the end of the InfiniteList.
  *
  * @param <T> The type of the elements in the infinite list.
  * @return The end of the InfiniteList.
  */
  public static <T> InfiniteList<T> end() {
    @SuppressWarnings("unchecked")
    InfiniteList<T> res = (InfiniteList<T>) END;
    return res;
  }
  
  /**
   * Checks whether it is the end of the InfiniteList and will return false as the InfiniteList
   * class is not the end.
   *  
   * @return false as it is not an End class.
   */
  public boolean isEnd() {
    return false;
  }
  
  /**
  * A static nested class to represent a list that contains nothing and it is used to mark
  * the end of the list.
  */
  private static class End extends InfiniteList<Object> {

    /** 
    * A private constructor to initialize the infinite list by providing the head and tail as null.
    */
    private End() {
      super(null, null);
    }

    /**
    * Throw NoSuchElementException() when head() is called in an End object. 
    */
    @Override
    public Object head() {
      throw new java.util.NoSuchElementException();
    }
    
    /**
    * Throw NoSuchElementException() when tail() is called in an End object. 
    */
    @Override
    public InfiniteList<Object> tail() {
      throw new java.util.NoSuchElementException();
    }

    /**
    * Return an End object when the map method is called in an End object.
    *
    * @param <R> The return type of the elements in the infinite list.
    * @param f The immutator function on the elements in the head and tail.
    * @return An End object.
    */
    @Override
    public <R> InfiniteList<R> map(Immutator<? extends R, ? super Object> f) {
      return InfiniteList.end();
    }

    /** 
    * Return the current End object when the filter method is called in an End object.
    *
    * @param pred The predicate immutator function on the elements in the head and tail.
    * @return The current End object.
    */
    @Override
    public InfiniteList<Object> filter(Immutator<Boolean, ? super Object> pred) {
      return this;
    }
    
    /** 
    * Return the current End object when the limit method is called in an End object.
    *
    * @param n The number of elements in the newly created finite list.
    * @return The current End object.
    */
    @Override
    public InfiniteList<Object> limit(long n) {
      return this;
    }

    /** 
    * Return the current End object when the takeWhile method is called in an End object.
    *
    * @param pred The predicate immutator function on the elements in the head and tail.
    * @return The current End object.
    */
    @Override
    public InfiniteList<Object> takeWhile(Immutator<Boolean, ? super Object> pred) {
      return this;
    }

    /** 
    * Return a newly initialized empty java.util.ArrayList toList method 
    * is called in an End object.
    *
    * @return The newly initialized empty java.util.ArrayList.
    */
    @Override
    public List<Object> toList() {
      return new ArrayList<>();
    }

    /** 
    * Return the identity in the parameter when the reduce method 
    * is called in an End object.
    *
    * @param <U> The type of the identity.
    * @param id The identity that the accumulator call the combine method on.
    * @param acc The combiner function that combines the identity and the element of the 
    *            InfiniteList.
    * @return The identity in the parameter.
    */
    @Override
    public <U> U reduce(U id, Combiner<U, U, ? super Object> acc) {
      return id;
    }

    /**
     * Return 0 when the count method is called in an End object.
     * 
     * @return The long 0.
     */
    @Override
    public long count() {
      return 0L;
    }

    /**
    * Checks whether it is the end of the InfiniteList and will return true as it is an End object.
    *  
    * @return true as it is an End class.
    */
    @Override
    public boolean isEnd() {
      return true;
    }

    /** 
    * Return - which represents the end of an infinite list.
    *
    * @return - which is the representation of the infinite list.
    */
    @Override
    public String toString() {
      return "-";
    }
  }
}