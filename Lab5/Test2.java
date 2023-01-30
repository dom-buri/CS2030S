import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class Test2 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    PrintStream old = System.out;
    ByteArrayOutputStream baos;
    PrintStream ps;
    
    cs2030s.fp.Constant<Integer> zero = new cs2030s.fp.Constant<>() {
      public Integer init() {
        return 0;
      }
    };
    cs2030s.fp.Action<Integer> print = new cs2030s.fp.Action<>() {
      public void call(Integer i) {
        System.out.println(i);
      }
    };
    
    try {
      we.expect(
        "Actually.<Number>ok(0).unwrap()",
        cs2030s.fp.Actually.<Number>ok(0).unwrap().toString(),
        "0"
      );
    } catch(Exception e) {
      System.out.println("Unexpected error occurs");
    }
    
    baos = new ByteArrayOutputStream();
    ps = new PrintStream(baos);
    System.setOut(ps);
    cs2030s.fp.Actually.<Integer>ok(9).finish(print);
    we.expectPrint("Actually.<Integer>ok(9).finish(print)",
                   "9",
                   baos,
                   old);
                   
    baos = new ByteArrayOutputStream();
    ps = new PrintStream(baos);
    System.setOut(ps);
    cs2030s.fp.Actually.<Integer>err(new Exception("Err")).finish(print);
    we.expectPrint("Actually.<Integer>err(new Exception(\"Err\")).finish(print)",
                   "",
                   baos,
                   old);
    
    we.expect(
      "Actually.<Number>ok(9).except(zero)",
      cs2030s.fp.Actually.<Number>ok(9).except(zero).toString(),
      "9"
    );
    we.expect(
      "Actually.<Number>err(new ArithmeticException(\"div by 0\")).except(zero)",
      cs2030s.fp.Actually.<Number>err(new ArithmeticException("div by 0")).except(zero).toString(),
      "0"
    );
    
    we.expect(
      "Actually.<Number>err(new ArithmeticException(\"div by 0\")).unless(4)",
      cs2030s.fp.Actually.<Number>err(new ArithmeticException("div by 0")).unless(4).toString(),
      "4"
    );
    we.expect(
      "Actually.<Number>ok(0).unless(4)",
      cs2030s.fp.Actually.<Number>ok(0).unless(4).toString(),
      "0"
    );
  }
}