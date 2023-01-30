import cs2030s.fp.Actually;
import cs2030s.fp.Immutator;
import cs2030s.fp.Constant;
import cs2030s.fp.Action;
import cs2030s.fp.Transformer;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

class Lab5 {
  public static String getGrade(String module, String student, String assessment,
      Map<String, Map<String, Map<String, String>>> db) {
    try {
      return db.get(module).get(student).get(assessment).toString();
    } catch(Exception e) {
      return "No such entry";
    }
  }
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
    String none = null;
    
    System.out.println(Actually.err(new ArithmeticException("Err")).equals(Actually.err(new Exception("Err"))));
    System.out.println(Actually.err(new ArithmeticException("Err")).equals(Actually.err(new Exception("Error"))));
    System.out.println(Actually.err(new ArithmeticException("Err")).equals(Actually.err(new Exception(none))));
    System.out.println(Actually.err(new ArithmeticException(none)).equals(Actually.err(new Exception(none))));
    System.out.println(Actually.err(new ArithmeticException("Err")).equals(Actually.ok("Err")));
    System.out.println(Actually.ok("Err").equals(Actually.ok("Err")));
    System.out.println(Actually.ok("Err").equals(Actually.err(new Exception("Error"))));
    System.out.println(Actually.ok("Err").equals("Err"));
    System.out.println(Actually.ok(null).equals(Actually.ok("Err")));
    System.out.println(Actually.ok(null).equals(Actually.ok(null)));
    System.out.println(Actually.ok(null).equals("Err"));
    System.out.println(Actually.ok(null).equals(null));
  }
  
  public static void test2() {
    Constant<Integer> zero = new Constant<>() {
      public Integer init() {
        return 0;
      }
    };
    Action<Integer> print = new Action<>() {
      public void call(Integer i) {
        System.out.println(i);
      }
    };
    
    try {
      Actually.<Number>ok(0).unwrap().toString();
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
    
    Actually.<Integer>ok(9).finish(print);
    Actually.<Integer>err(new Exception("Err")).finish(print);
    System.out.println(Actually.<Number>ok(9).except(zero).toString());
    System.out.println(Actually.<Number>err(new ArithmeticException("div by 0")).except(zero).toString());
    System.out.println(Actually.<Number>err(new ArithmeticException("div by 0")).unless(4).toString());
    System.out.println(Actually.<Number>ok(0).unless(4).toString());
  }
  
  public static void test3() {
    Immutator<Integer,Integer> inc = new Immutator<>() {
      public Integer invoke(Integer p) {
        return p+1;
      }
    };
    Immutator<Integer,Integer> inv = new Immutator<>() {
      public Integer invoke(Integer p) {
        return 1/p;
      }
    };
    Immutator<Number,Integer> incNum = new Immutator<>() {
      public Number invoke(Integer p) {
        return p+1;
      }
    };
    Immutator<Number,Integer> invNum = new Immutator<>() {
      public Number invoke(Integer p) {
        return 1/p;
      }
    };
    
    System.out.println(Actually.<Integer>ok(0).transform(inc).toString());
    System.out.println(Actually.<Integer>ok(0).transform(inv).toString());
    System.out.println(Actually.ok(0).transform(inc).toString());
    System.out.println(Actually.ok(0).transform(inv).toString());
    System.out.println(Actually.<Integer>ok(0).transform(incNum).toString());
    System.out.println(Actually.<Integer>ok(0).transform(invNum).toString());
    System.out.println(Actually.ok(0).transform(incNum).toString());
    System.out.println(Actually.ok(0).transform(invNum).toString());
  }
  
  public static void test4() {
    Transformer<Integer,Integer> inc = new Transformer<>() {
      public Integer invoke(Integer p) {
        return p+1;
      }
    };
    Transformer<Integer,Integer> sqr = new Transformer<>() {
      public Integer invoke(Integer p) {
        return p*p;
      }
    };
    
    Transformer<Integer,Integer> sqrPlusOneA = sqr.before(inc);
    Transformer<Integer,Integer> sqrPlusOneB = inc.after(sqr);
    Transformer<Integer,Integer> plusOneSqrA = sqr.after(inc);
    Transformer<Integer,Integer> plusOneSqrB = inc.before(sqr);
    
    System.out.println(sqrPlusOneA.invoke(2).toString());
    System.out.println(sqrPlusOneA.invoke(2).toString());
    System.out.println(plusOneSqrA.invoke(2).toString());
    System.out.println(plusOneSqrB.invoke(2).toString());
  }
  
  public static void test5() {
    Immutator<Actually<Integer>,Integer> half = new Immutator<>() {
      public Actually<Integer> invoke(Integer p) {
        if (p%2 == 0) {
          return Actually.<Integer>ok(p/2);
        } else {
          return Actually.<Integer>err(new Exception("odd number"));
        }
      }
    };
    Immutator<Actually<Integer>,Integer> inc = new Immutator<>() {
      public Actually<Integer> invoke(Integer p) {
        return Actually.<Integer>ok(p+1);
      }
    };
    Immutator<Actually<Integer>,Integer> make = new Immutator<>() {
      public Actually<Integer> invoke(Integer p) {
        return Actually.<Integer>ok(p);
      }
    };
    Constant<Integer> zero = new Constant<>() {
      public Integer init() {
        return 0;
      }
    };
    
    System.out.println(make.invoke(0).next(inc).next(inc).next(half).toString());
    System.out.println(make.invoke(0).next(inc).next(half).next(inc).toString());
    System.out.println(make.invoke(0).next(inc).next(inc).next(half).except(zero).toString());
    System.out.println(make.invoke(0).next(inc).next(half).next(inc).except(zero).toString());
  }
  
  public static void test6() {
    Map<String, Map<String, Map<String, String>>> nus =
          Map.of(
            "CS2030S", Map.of(
              "Steve", Map.of(
                "lab1", "A",
                "lab2", "A-",
                "lab3", "A+",
                "lab4", "B",
                "pe1", "C"
              ),
              "Tony", Map.of(
                "lab1", "C",
                "lab2", "C",
                "lab3", "B-",
                "lab4", "B+",
                "pe1", "A"
              )
            ),
            "CS2040S", Map.of(
              "Steve", Map.of(
                "lab1", "A",
                "lab2", "A+",
                "lab3", "A+",
                "lab4", "A",
                "midterm", "A+"
              )
            )
          );

    System.out.println(getGrade("CS2030S", "Steve", "lab1", nus));
    System.out.println(getGrade("CS2030S", "Steve", "lab2", nus));
    System.out.println(getGrade("CS2040S", "Steve", "lab3", nus));
    System.out.println(getGrade("CS2040S", "Steve", "lab4", nus));
    System.out.println(getGrade("CS2030S", "Tony", "lab1", nus));
    System.out.println(getGrade("CS2030S", "Tony", "midterm", nus));
    System.out.println(getGrade("CS2040S", "Tony", "lab4", nus));
    System.out.println(getGrade("CS2040S", "Bruce", "lab4", nus));
  }
}
