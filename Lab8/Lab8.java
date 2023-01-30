import cs2030s.fp.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Lab8 {
  public static void main(String[] args) {
    // Create a scanner to read from standard input.
    Scanner sc = new Scanner(System.in);

    // Read a single integer from the test file
    // and then run the appropriate test case
    switch (sc.nextInt()) {
      case 1:
        test1();
        break;
      case 2:
        test2();
        break;
      case 3:
        test3();
        break;
      case 4:
        test4();
        break;
      case 5:
        test5();
        break;
      case 6:
        test6();
        break;
    }
  }
  
  public static void test1() {
    System.out.println(InfiniteList.generate(() -> 1).toString());
    System.out.println(InfiniteList.generate(() -> 1).head());
    System.out.println(InfiniteList.generate(() -> null).tail().head());
    System.out.println(InfiniteList.iterate("A", x -> x + "Z").head());
    System.out.println(InfiniteList.iterate("A", x -> x + "Z").tail().head());
    System.out.println(InfiniteList.iterate("A", x -> x + "Z").tail().tail().head());
    
    List<Integer> evalHistory = new ArrayList<>();
    Immutator<Integer, Integer> op = x -> { 
      evalHistory.add(x); 
      return x + 1; 
    };
    
    InfiniteList<Integer> numbers = InfiniteList.iterate(1, op);
    
    System.out.println(numbers.head());
    System.out.println(numbers.toString());
    System.out.println(evalHistory);
    
    System.out.println(numbers.tail().head());
    System.out.println(numbers.toString());
    System.out.println(evalHistory);
    
    System.out.println(numbers.tail().head());
    System.out.println(numbers.toString());
    System.out.println(evalHistory);
    
    System.out.println(numbers.tail().tail().head());
    System.out.println(numbers.toString());
    System.out.println(evalHistory);
    
    System.out.println(numbers.tail().head());
    System.out.println(numbers.toString());
    System.out.println(evalHistory);
    
    InfiniteList<Integer> zeros = InfiniteList.generate(() -> { 
      evalHistory.add(0); 
      return 0; 
    });
    evalHistory.retainAll(List.of());
    System.out.println(zeros.toString());
    System.out.println(evalHistory);
    
    System.out.println(zeros.head());
    System.out.println(zeros.toString());
    System.out.println(evalHistory);
    
    System.out.println(zeros.tail().head());
    System.out.println(zeros.toString());
    System.out.println(evalHistory);
    
    System.out.println(zeros.head());
    System.out.println(zeros.toString());
    System.out.println(evalHistory);
    
    System.out.println(zeros.tail().head());
    System.out.println(zeros.toString());
    System.out.println(evalHistory);
    
    System.out.println(zeros.tail().tail().head());
    System.out.println(zeros.toString());
    System.out.println(evalHistory);
    
    System.out.println(zeros.tail().head());
    System.out.println(zeros.toString());
    System.out.println(evalHistory);
  }
  
  public static void test2() {
    System.out.println(InfiniteList.generate(() -> 1).map(x -> x * 2).toString());
    System.out.println(InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).toString());
    System.out.println(InfiniteList.generate(() -> 1).map(x -> x * 2).head());
    System.out.println(InfiniteList.generate(() -> 1).map(x -> x * 2).tail().head());
    System.out.println(InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).head());
    System.out.println(InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).tail().head());
    System.out.println(InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).map(x -> x - 1).head());
    System.out.println(InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).map(x -> x - 1).tail().head());
    System.out.println(InfiniteList.iterate(1, x -> x + 1).map(x -> x % 2 == 0 ? null : x).tail().head());
    
    List<Integer> doublerHistory = new ArrayList<>();
    List<Integer> generateHistory = new ArrayList<>();
    Constant<Integer> generator = () -> { 
      generateHistory.add(1); 
      return 1; 
    };
    Immutator<Integer, Integer> doubler = x -> { 
      doublerHistory.add(x); 
      return x * 2; 
    };
    
    System.out.println(InfiniteList.generate(generator).map(doubler).tail().head());
    System.out.println(generateHistory);
    System.out.println(doublerHistory);
    
    generateHistory.retainAll(List.of());
    doublerHistory.retainAll(List.of());
    InfiniteList<Integer> ones = InfiniteList.generate(generator);
    InfiniteList<Integer> twos = ones.map(doubler);
    
    System.out.println(twos.tail().head());
    System.out.println(ones.toString());
    System.out.println(twos.toString());
    System.out.println(generateHistory);
    System.out.println(doublerHistory);
    
    System.out.println(twos.head());
    System.out.println(generateHistory);
    System.out.println(doublerHistory);
    
    System.out.println(twos.tail().head());
    System.out.println(generateHistory);
    System.out.println(doublerHistory);
  }
  
  public static void test3() {
    System.out.println(InfiniteList.generate(() -> 1).filter(x -> x % 2 == 0).toString());
    System.out.println(InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).toString());
    System.out.println(InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).head());
    System.out.println(InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).filter(x -> x > 4).head());
    
    List<Integer> incrHistory = new ArrayList<>();
    List<Integer> isEvenHistory = new ArrayList<>();
    Immutator<Boolean, Integer> isEven = x -> { 
      isEvenHistory.add(x); 
      return x % 2 == 0; 
    };
    Immutator<Integer, Integer> incr = x -> { 
      incrHistory.add(x); 
      return x + 1; 
    };
    
    System.out.println(InfiniteList.iterate(1, incr).filter(isEven).tail().head());
    System.out.println(incrHistory);
    System.out.println(isEvenHistory);

    incrHistory.retainAll(List.of());
    isEvenHistory.retainAll(List.of());

    InfiniteList<Integer> nums = InfiniteList.iterate(1, incr);
    InfiniteList<Integer> evens = nums.filter(isEven);
    
    System.out.println(evens.tail().head());
    System.out.println(nums.toString());
    System.out.println(evens.toString());
    System.out.println(nums.tail().head());
    System.out.println(incrHistory);
    System.out.println(isEvenHistory);
    
    System.out.println(evens.tail().head());
    System.out.println(incrHistory);
    System.out.println(isEvenHistory);

    incrHistory.retainAll(List.of());
    isEvenHistory.retainAll(List.of());

    List<Integer> moreThan5History = new ArrayList<>();
    Immutator<Boolean, Integer> moreThan5 = x -> { 
      moreThan5History.add(x); 
      return x > 5; 
    };
    
    System.out.println(InfiniteList.iterate(1, incr).filter(moreThan5).filter(isEven).head());
    
    System.out.println(incrHistory);
    System.out.println(isEvenHistory);
    System.out.println(moreThan5History);

    incrHistory.retainAll(List.of());
    isEvenHistory.retainAll(List.of());
    moreThan5History.retainAll(List.of());

    List<Integer> doublerHistory = new ArrayList<>();
    Immutator<Integer, Integer> doubler = x -> { 
      doublerHistory.add(x); 
      return x * 2; 
    };
    
    System.out.println(InfiniteList.iterate(1, incr).map(doubler).filter(moreThan5).filter(isEven)
        .tail().head());
    
    System.out.println(incrHistory);
    System.out.println(isEvenHistory);
    System.out.println(moreThan5History);
    System.out.println(doublerHistory);

    doublerHistory.retainAll(List.of());
    incrHistory.retainAll(List.of());
    isEvenHistory.retainAll(List.of());
    moreThan5History.retainAll(List.of());
    
    System.out.println(InfiniteList.iterate(1, incr).filter(isEven).map(doubler).filter(moreThan5).head());
    
    System.out.println(incrHistory);
    System.out.println(isEvenHistory);
    System.out.println(moreThan5History);
    System.out.println(doublerHistory);
  }
  
  public static void test4() {
    System.out.println(InfiniteList.iterate(1, x -> x + 1).isEnd());
    System.out.println(InfiniteList.generate(() -> 2).isEnd());
    System.out.println(InfiniteList.generate(() -> 2).filter(x -> x % 3 == 0).isEnd());
    System.out.println(InfiniteList.iterate(1, x -> x + 1).map(x -> 2).isEnd());
    System.out.println(InfiniteList.iterate(1, x -> x + 1).map(x -> 2).isEnd());
    System.out.println(InfiniteList.end().isEnd());
    System.out.println(InfiniteList.end().map(x -> 2).isEnd());
    System.out.println(InfiniteList.end().filter(x -> true).isEnd());
    System.out.println(InfiniteList.end().filter(x -> false).isEnd());
    System.out.println(InfiniteList.end().limit(4).isEnd());
    System.out.println(InfiniteList.iterate(1, x -> x + 1).limit(0).isEnd());
    System.out.println(InfiniteList.iterate(1, x -> x + 1).limit(0).isEnd());
    System.out.println(InfiniteList.iterate(1, x -> x + 1).limit(1).isEnd());
    System.out.println(InfiniteList.iterate(1, x -> x + 1).limit(10).isEnd());
    System.out.println(InfiniteList.iterate(1, x -> x + 1).limit(-1).isEnd());

    List<Integer> incrHistory = new ArrayList<>();
    Immutator<Integer, Integer> incr = x -> {
      incrHistory.add(x);
      return x + 1;
    };
    
    System.out.println(InfiniteList.iterate(1, incr).limit(0).isEnd());
    System.out.println(incrHistory);
    
    System.out.println(InfiniteList.iterate(1, incr).limit(1).isEnd());
    System.out.println(incrHistory);
    
    System.out.println(InfiniteList.iterate(1, incr).limit(10).isEnd());
    System.out.println(incrHistory);
    
    System.out.println(InfiniteList.generate(() -> 1).limit(4).toString());
    System.out.println(InfiniteList.iterate(1, incr).limit(4).toString());
    
    try {
      System.out.println(InfiniteList.iterate(1, incr).limit(0).head());
    } catch(java.util.NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(InfiniteList.iterate(1, incr).limit(1).head());
    System.out.println(InfiniteList.iterate(1, incr).limit(4).head());
    try {
      System.out.println(InfiniteList.iterate(1, incr).limit(1).tail().head());
    } catch(java.util.NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(InfiniteList.iterate(1, incr).limit(4).tail().tail().head());
    try {
      System.out.println(InfiniteList.iterate(1, incr).limit(4).limit(1).tail().head());
    } catch(java.util.NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    try {
      System.out.println(InfiniteList.iterate(1, incr).limit(1).limit(4).tail().head());
    } catch(java.util.NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    
    Immutator<Boolean, Integer> isEven = x -> (x % 2 == 0);
    
    try {
      System.out.println(InfiniteList.iterate(1, incr).filter(isEven).limit(0).head());
    } catch(java.util.NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(InfiniteList.iterate(1, incr).filter(isEven).limit(1).head());
    try {
      System.out.println(InfiniteList.iterate(1, incr).limit(1).filter(isEven).head());
    } catch(java.util.NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(InfiniteList.iterate(1, incr).limit(2).filter(isEven).head());
    System.out.println(InfiniteList.iterate("A", s -> s + "Z").limit(2).map(s -> s.length()).head());
    System.out.println(InfiniteList.iterate("A", s -> s + "Z").limit(2).map(s -> s.length())
        .tail().head());
        
    try {
      System.out.println(InfiniteList.iterate("A", s -> s + "Z").limit(2).map(s -> s.length()).tail().tail()
        .head());
    } catch(java.util.NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(InfiniteList.iterate("A", s -> s + "Z").map(s -> s.length()).head());
    System.out.println(InfiniteList.iterate("A", s -> s + "Z").map(s -> s.length()).tail().head());
    try {
      System.out.println(InfiniteList.iterate("A", s -> s + "Z").map(s -> s.length()).limit(2).tail().tail()
        .head());
    } catch(java.util.NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(InfiniteList.<String>end().toList());
    System.out.println(InfiniteList.iterate("A", s -> s + "Z").map(s -> s.length()).limit(2).toList());
    System.out.println(InfiniteList.iterate("A", s -> s + "Z").limit(2).map(s -> s.length()).toList());
    System.out.println(InfiniteList.iterate(1, incr).limit(2).filter(isEven).toList());
    System.out.println(InfiniteList.iterate(1, incr).filter(isEven).limit(2).toList());
    System.out.println(InfiniteList.iterate(1, x -> x + 1).limit(10).limit(3).toList());
    System.out.println(InfiniteList.iterate(1, x -> x + 1).limit(3).limit(10).toList());
    System.out.println(InfiniteList.generate(() -> 4).limit(0).toList());
    System.out.println(InfiniteList.generate(() -> 4).limit(2).toList());
    System.out.println(InfiniteList.iterate(0, x -> x + 1).filter(x -> x > 10).map(x -> x.hashCode() % 30)
        .filter(x -> x < 20).limit(5).toList());
        
    java.util.Random rng = new java.util.Random(1);
    System.out.println(InfiniteList.generate(() -> rng.nextInt() % 100).filter(x -> x > 10).limit(4)
        .toList());
    System.out.println(InfiniteList.<Object>generate(() -> null).limit(4).limit(1).toList());
    System.out.println(InfiniteList.<Object>generate(() -> null).limit(1).limit(4).toList());
  }
  
  public static void test5() {
    List<Integer> incrHistory = new ArrayList<>();
    Immutator<Integer, Integer> incr = x -> { 
      incrHistory.add(x);
      return x + 1;
    };

    List<Integer> lessThan0History = new ArrayList<>();
    Immutator<Boolean, Integer> lessThan0 = x -> { 
      lessThan0History.add(x);
      return x < 0;
    };
    
    System.out.println(InfiniteList.<Integer>end().takeWhile(x -> x < 0).isEnd());
    System.out.println(InfiniteList.iterate(1, incr).takeWhile(lessThan0).isEnd());
    System.out.println(incrHistory);
    System.out.println(lessThan0History);

    List<Integer> lessThan2History = new ArrayList<>();
    Immutator<Boolean, Integer> lessThan2 = x -> { 
      lessThan2History.add(x);
      return x < 2;
    };
    incrHistory.retainAll(List.of());
    lessThan2History.retainAll(List.of());
    
    System.out.println(InfiniteList.iterate(1, incr).takeWhile(lessThan2).isEnd());
    System.out.println(incrHistory);
    System.out.println(lessThan0History);
    System.out.println(lessThan2History);
    
    System.out.println(InfiniteList.iterate(1, incr).takeWhile(x -> x < 5).takeWhile(x -> x < 3).toList());
    System.out.println(InfiniteList.iterate(1, incr).filter(x -> x % 2 == 0).takeWhile(x -> x < 10).toList());
    System.out.println(InfiniteList.generate(() -> 2).takeWhile(lessThan0).toString());
    System.out.println(InfiniteList.iterate(1, incr).takeWhile(lessThan0).toString());
    System.out.println(incrHistory);
    System.out.println(lessThan0History);
    System.out.println(lessThan2History);
    
    incrHistory.retainAll(List.of());
    lessThan0History.retainAll(List.of());
    
    try {
      System.out.println(InfiniteList.iterate(1, incr).takeWhile(lessThan0).head());
    } catch(java.util.NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(incrHistory);
    System.out.println(lessThan0History);
    System.out.println(lessThan2History);

    incrHistory.retainAll(List.of());
    lessThan2History.retainAll(List.of());
    
    System.out.println(InfiniteList.iterate(1, incr).takeWhile(lessThan2).head());
    System.out.println(incrHistory);
    System.out.println(lessThan0History);
    System.out.println(lessThan2History);
    
    incrHistory.retainAll(List.of());
    lessThan2History.retainAll(List.of());
    
    try {
      System.out.println(InfiniteList.iterate(1, incr).takeWhile(lessThan2).tail().head());
    } catch(java.util.NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(incrHistory);
    System.out.println(lessThan0History);
    System.out.println(lessThan2History);

    incrHistory.retainAll(List.of());
    lessThan2History.retainAll(List.of());
    lessThan0History.retainAll(List.of());
    
    try {
      System.out.println(InfiniteList.iterate(1, incr).takeWhile(lessThan2).takeWhile(lessThan0).head());
    } catch(java.util.NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(incrHistory);
    System.out.println(lessThan0History);
    System.out.println(lessThan2History);

    incrHistory.retainAll(List.of());
    lessThan2History.retainAll(List.of());
    lessThan0History.retainAll(List.of());
    
    try {
      System.out.println(InfiniteList.iterate(1, incr).takeWhile(lessThan0).takeWhile(lessThan2).head());
    } catch(java.util.NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(incrHistory);
    System.out.println(lessThan0History);
    System.out.println(lessThan2History);

    List<Integer> lessThan5History = new ArrayList<>();
    Immutator<Boolean, Integer> lessThan5 = x -> { 
      lessThan5History.add(x);
      return x < 5;
    };

    incrHistory.retainAll(List.of());
    lessThan2History.retainAll(List.of());
    
    try {
      System.out.println(InfiniteList.iterate(1, incr).takeWhile(lessThan5).takeWhile(lessThan2).tail().head());
    } catch(java.util.NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(incrHistory);
    System.out.println(lessThan0History);
    System.out.println(lessThan2History);
    System.out.println(lessThan5History);

    List<Integer> isEvenHistory = new ArrayList<>();
    Immutator<Boolean, Integer> isEven = x -> { 
      isEvenHistory.add(x);
      return x % 2 == 0;
    };

    List<Integer> lessThan10History = new ArrayList<>();
    Immutator<Boolean, Integer> lessThan10 = x -> { 
      lessThan10History.add(x);
      return x < 10;
    };
    
    incrHistory.retainAll(List.of());
    lessThan10History.retainAll(List.of());
    
    System.out.println(InfiniteList.iterate(1, incr).filter(isEven).takeWhile(lessThan10).head());
    System.out.println(incrHistory);
    System.out.println(lessThan0History);
    System.out.println(lessThan2History);
    System.out.println(lessThan5History);
    System.out.println(lessThan10History);
    System.out.println(isEvenHistory);

    incrHistory.retainAll(List.of());
    isEvenHistory.retainAll(List.of());
    lessThan10History.retainAll(List.of());
    
    System.out.println(InfiniteList.iterate(1, incr).filter(isEven).takeWhile(lessThan10).tail().head());
    System.out.println(incrHistory);
    System.out.println(lessThan0History);
    System.out.println(lessThan2History);
    System.out.println(lessThan5History);
    System.out.println(lessThan10History);
    System.out.println(isEvenHistory);

    incrHistory.retainAll(List.of());
    lessThan10History.retainAll(List.of());
    InfiniteList<Integer> list = InfiniteList.iterate(1, incr).takeWhile(lessThan10);
    System.out.println(list.tail().tail().head());
    System.out.println(list.head());
    System.out.println(incrHistory);
    System.out.println(lessThan0History);
    System.out.println(lessThan2History);
    System.out.println(lessThan5History);
    System.out.println(lessThan10History);
    System.out.println(isEvenHistory);
    
    System.out.println(list.tail().head());
    System.out.println(incrHistory);
    System.out.println(lessThan0History);
    System.out.println(lessThan2History);
    System.out.println(lessThan5History);
    System.out.println(lessThan10History);
    System.out.println(isEvenHistory);
    
    System.out.println(list.tail().tail().tail().head());
    System.out.println(incrHistory);
    System.out.println(lessThan0History);
    System.out.println(lessThan2History);
    System.out.println(lessThan5History);
    System.out.println(lessThan10History);
    System.out.println(isEvenHistory);
  }
  
  public static void test6() {
    InfiniteList<Integer> numbers = InfiniteList.iterate(0, x -> x + 1);
    System.out.println(InfiniteList.<Integer>end().reduce(0, (x, y) -> x + y));
    System.out.println(numbers.limit(5).reduce(0, (x, y) -> x + y));
    System.out.println(InfiniteList.iterate(0, x -> x + 1).limit(0).reduce(0, (x, y) -> x + y));
    System.out.println(InfiniteList.iterate(1, x -> x + 1).map(x -> x * x).limit(5).reduce(1, (x, y) -> x * y));
    
    System.out.println(InfiniteList.end().count());
    System.out.println(numbers.limit(0).count());
    System.out.println(numbers.limit(1).count());
    System.out.println(numbers.filter(x -> x % 2 == 1).limit(10).count());
    System.out.println(numbers.limit(10).filter(x -> x % 2 == 1).count());
    System.out.println(numbers.takeWhile(x -> x < 10).count());
    System.out.println(numbers.takeWhile(x -> x < 10).filter(x -> x % 2 == 0).count());
    
    System.out.println(InfiniteList.end().count() == 0L);
    System.out.println(numbers.limit(0).count() == 0L);
    System.out.println(numbers.limit(1).count() == 1L);
    System.out.println(numbers.filter(x -> x % 2 == 1).limit(10).count() == 10L);
    System.out.println(numbers.limit(10).filter(x -> x % 2 == 1).count() == 5L);
    System.out.println(numbers.takeWhile(x -> x < 10).count() == 10L);
    System.out.println(numbers.takeWhile(x -> x < 10).filter(x -> x % 2 == 0).count() == 5L);
  }
}
