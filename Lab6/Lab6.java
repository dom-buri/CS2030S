import cs2030s.fp.*;
import java.util.Scanner;

class Lab6 {
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
    }
  }
  
  public static void test1() {
    Lazy<Integer> mod1 = Lazy.from(2030);
    System.out.println(mod1.toString());
    
    Lazy<String> mod2 = Lazy.from(() -> "CS2030S");
    System.out.println(mod2.toString());
    
    Lazy<String> hello = Lazy.from(() -> {
      System.out.println("world!");
      return "hello";
    });
    System.out.println(hello.get().toString());
    System.out.println(hello.get().toString());
  }
  
  public static void test2() {
    Memo<Integer> mod1 = Memo.from(2030);
    System.out.println(mod1.toString());
    
    Memo<String> mod2 = Memo.from(() -> "CS2030S");
    System.out.println(mod2.toString());
    System.out.println(mod2.get());
    System.out.println(mod2.toString());
    
    Memo<String> hello = Memo.from(() -> {
      System.out.println("world!");
      return "hello";
    });
    System.out.println(hello.get().toString());
    System.out.println(hello.get().toString());
  }
  
  public static void test3() {
    Constant<String> password = () -> "123456";
    Lazy<String> lazy = Lazy.from(password);
    System.out.println(lazy.toString());
    System.out.println(lazy.transform(str -> str.substring(0, 1)).toString());
    
    Memo<String> memo = Memo.from(password);
    System.out.println(memo.toString());
    System.out.println(memo.transform(str -> str.substring(0, 1)).toString());
    System.out.println(memo.transform(str -> str.substring(0, 1)).get().toString());
    System.out.println(memo.toString());
    
    Immutator<Integer, String> len = str -> {
      System.out.println("length");
      return str.length();
    };
    Lazy<Integer> lazyLen = lazy.transform(len);
    System.out.println(lazyLen.toString());
    System.out.println(lazyLen.get());
    System.out.println(lazyLen.get());
    
    Memo<Integer> step1 = Memo.from(1010);
    Memo<Integer> step2 = step1.transform(i -> i * 2);
    Memo<Integer> step3 = step2.next(i -> Memo.from(i + 10));
    System.out.println(step1.toString());
    System.out.println(step2.toString());
    System.out.println(step3.toString());
    System.out.println(step3.get());
    System.out.println(step2.toString());
    System.out.println(step1.toString());
    
    Memo<Integer> noErr = Memo.from(0);
    Memo<Integer> err = noErr.transform(x -> 1/x);
    try {
      System.out.println(err.get());
    } catch(ArithmeticException ae) {
      System.out.println(ae.getMessage());
    }
  }
  
  public static void test4() {
    Combiner<String, Integer, Integer> concat = (x, y) -> {
      System.out.println("combine");
      return x.toString() + y.toString();
    };
    
    Memo<Integer> twenty, thirty, modInt;
    twenty = Memo.from(() -> 20);
    thirty = Memo.from(() -> 30);
    modInt = twenty.combine(thirty, (x, y) -> x * 100 + y);
    Memo<String> modStr = twenty.combine(thirty, concat);
    
    System.out.println(twenty.toString());
    System.out.println(thirty.toString());
    System.out.println(modInt.toString());
    System.out.println(modStr.toString());
    System.out.println(modStr.get().toString());
    System.out.println(twenty.toString());
    System.out.println(thirty.toString());
    System.out.println(modInt.toString());
    
    Combiner<String, Integer, Double> comb = (x, y) -> x.toString() + " + " + y.toString();
    Memo<String> s = modInt.combine(Memo.from(0.1), comb);
    System.out.println(s.toString());
    System.out.println(s.get());
    System.out.println(modInt.toString());
    
    Memo<Integer> x1 = Memo.from(1);
    for (int i = 0; i < 10; i ++) {
      final Memo<Integer> y1 = x1; // final just to ensure it is unchanged
      final int j = i;
      x1 = Memo.from(() -> { System.out.println(j); return y1.get() + y1.get(); });
    }
    System.out.println(x1.get());
  }
  
  public static void test5() {
    Constant<Boolean> t = new Constant<>() {
      public Boolean init() {
        return true;
      }
    };
    Constant<Boolean> f = new Constant<>() {
      public Boolean init() {
        String res = "";
        for (int i=0; i<100000; i++) {
          res += i;
        }
        return false;
      }
    };
    Cond cond = new And(new Or(new Bool(t), new Bool(f)), new Not(new Not(new Bool(t))));
    
    System.out.println(cond.toString());
    System.out.println(cond.neg().toString());
    System.out.println(cond.neg().neg().toString());
    System.out.println(cond.eval());
    System.out.println(cond.neg().toString());
    System.out.println(cond.neg().neg().toString());
  }
}
