import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * The main class for CS2030S Lab 4.
 * @author Dominic Ng (Group 16B)
 * @version CS2030S AY21/22 Semester 2
 */
class Lab4 {
    
  /**
   * Inner class for testing.
   */
  static class Incr implements Immutator<Integer, Integer> {
    public Integer invoke(Integer t1) {
      return t1 + 1;
    }
  }
  /**
   * Inner class for testing.
   */

  static class Length implements Immutator<Integer, String> {
    public Integer invoke(String t1) {
      return t1.length();
    }
  }
    
  /**
   * Helper method to clean a string from
   * any newline.
   *
   * @param txt Input string.
   * @return The cleaned string.
   */
  public static String clean(String txt) {
    String res = "";
    for (int i = 0; i < txt.length(); i++) {
      if (txt.charAt(i) != '\r' && txt.charAt(i) != '\n') {
        res += txt.charAt(i);
      }
    }
    return res;
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
      default:
        break;
    }

    // Clean up the scanner.
    sc.close();
  }
  
  /**
   * Test #1.
   */
  public static void test1() {
    PrintStream old = System.out;
    ByteArrayOutputStream baos;
    PrintStream ps;
    
    try {
      baos = new ByteArrayOutputStream();
      ps = new PrintStream(baos);
      System.setOut(ps);
      
      new Print().call(17);
      System.out.flush();
      System.setOut(old);
      
      System.out.println(Lab4.clean(baos.toString()));
    } catch (Exception e) {
      System.out.println("Error occurred");
    }
    
    try {
      baos = new ByteArrayOutputStream();
      ps = new PrintStream(baos);
      System.setOut(ps);
      
      new Print().call("string");
      System.out.flush();
      System.setOut(old);
      
      System.out.println(Lab4.clean(baos.toString()));
    } catch (Exception e) {
      System.out.println("Error occurred");
    }
  }
  
  /**
   * Test #2.
   */
  public static void test2() {
    PrintStream old = System.out;
    ByteArrayOutputStream baos;
    PrintStream ps;
    
    try {
      baos = new ByteArrayOutputStream();
      ps = new PrintStream(baos);
      System.setOut(ps);
      
      Probably.just(4).act(new Print());
      System.out.flush();
      System.setOut(old);
      
      System.out.println(Lab4.clean(baos.toString()));
    } catch (Exception e) {
      System.out.println("Error occurred");
    }
    
    try {
      baos = new ByteArrayOutputStream();
      ps = new PrintStream(baos);
      System.setOut(ps);
      
      Probably.just("string").act(new Print());
      System.out.flush();
      System.setOut(old);
      
      System.out.println(Lab4.clean(baos.toString()));
    } catch (Exception e) {
      System.out.println("Error occurred");
    }
    
    try {
      baos = new ByteArrayOutputStream();
      ps = new PrintStream(baos);
      System.setOut(ps);
      
      Probably.none().act(new Print());
      System.out.flush();
      System.setOut(old);
      
      System.out.println(Lab4.clean(baos.toString()));
    } catch (Exception e) {
      System.out.println("Error occurred");
    }
  }
  
  /**
   * Test #3.
   */
  public static void test3() {
    try {
      System.out.println(new Incr().invoke(4).toString());
      System.out.println(new Incr().invoke(new Incr().invoke(4)).toString());
      System.out.println(new Length().invoke("string").toString());
      System.out.println(new Incr().invoke(new Length().invoke("string")).toString());
      
      System.out.println(new Improbable<Integer>().invoke(1).toString());
      System.out.println(new Improbable<String>().invoke(null).toString());
      System.out.println(new Improbable<Integer>().invoke(1).transform(new Incr()).toString());
      System.out.println(new Improbable<>().invoke(new Improbable<Integer>().invoke(1)).toString());
    } catch (Exception e) {
      System.out.println("Error occurred");
    }
  }
  
  /**
   * Test #4.
   */
  public static void test4() {
    try {
      System.out.println(Probably.just(4).transform(new Incr()).toString());
      System.out.println(Probably.just(4).transform(new Incr()).transform(new Incr()).toString());
      System.out.println(Probably.just("string").transform(new Length()).toString());
      System.out.println(Probably.just("string").transform(new Length())
                        .transform(new Incr()).toString());
      
      System.out.println(Probably.<Integer>none().transform(new Incr()).toString());
      System.out.println(Probably.<String>none().transform(new Length()).toString());
      System.out.println(Probably.<String>just(null).transform(new Length())
                        .transform(new Incr()).toString());
    } catch (Exception e) {
      System.out.println("Error occurred");
    }
  }
  
  /**
   * Test #5.
   */
  public static void test5() {
    // Probably<Immutator<Integer, Integer>> justIncr = Probably.just(new Incr());
    // Probably<Immutator<Integer, String>> justLength = Probably.just(new Length());
    // Probably<Immutator<Integer, Integer>> noIncr = Probably.none();
    // Probably<Immutator<Integer, String>> noLength = Probably.none();
    
    try {
      System.out.println(Probably.just(17).check(new IsModEq(3, 2)).toString());
      System.out.println(Probably.just(18).check(new IsModEq(3, 2)).toString());
      
      System.out.println(Probably.just(16).transform(new Incr())
                        .check(new IsModEq(3, 2)).toString());
      System.out.println(Probably.just("string").transform(new Length()).transform(new Incr())
                         .transform(new Incr()).check(new IsModEq(3, 2)).toString());
      System.out.println(Probably.<Integer>just(null).check(new IsModEq(0, 2)).toString());
    } catch (Exception e) {
      System.out.println("Error occurred");
    }
  }
  
  /**
   * Test #6.
   */
  public static void test6() {
    Probably<Immutator<Integer, Integer>> justIncr = Probably.just(new Incr());
    Probably<Immutator<Integer, String>> justLength = Probably.just(new Length());
    Probably<Immutator<Integer, Integer>> noIncr = Probably.none();
    Probably<Immutator<Integer, String>> noLength = Probably.none();
    
    try {
      System.out.println(Probably.just(17).apply(justIncr).toString());
      System.out.println(Probably.<Integer>none().apply(justIncr).toString());
      System.out.println(Probably.just(17).apply(noIncr).toString());
      System.out.println(Probably.<Integer>none().apply(noIncr).toString());
      
      System.out.println(Probably.just("string").apply(justLength).toString());
      System.out.println(Probably.<String>none().apply(justLength).toString());
      System.out.println(Probably.just("string").apply(noLength).toString());
      System.out.println(Probably.<String>none().apply(noLength).toString());
    } catch (Exception e) {
      System.out.println("Error occurred");
    }
  }
}
