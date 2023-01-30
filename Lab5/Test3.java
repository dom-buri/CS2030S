import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class Test3 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    
    cs2030s.fp.Immutator<Integer,Integer> inc = new cs2030s.fp.Immutator<>() {
      public Integer invoke(Integer p) {
        return p+1;
      }
    };
    cs2030s.fp.Immutator<Integer,Integer> inv = new cs2030s.fp.Immutator<>() {
      public Integer invoke(Integer p) {
        return 1/p;
      }
    };
    cs2030s.fp.Immutator<Number,Integer> incNum = new cs2030s.fp.Immutator<>() {
      public Number invoke(Integer p) {
        return p+1;
      }
    };
    cs2030s.fp.Immutator<Number,Integer> invNum = new cs2030s.fp.Immutator<>() {
      public Number invoke(Integer p) {
        return 1/p;
      }
    };
    
    we.expect(
      "Actually.<Integer>ok(0).transform(inc)",
      cs2030s.fp.Actually.<Integer>ok(0).transform(inc).toString(),
      "<1>"
    );
    we.expect(
      "Actually.<Integer>ok(0).transform(inv)",
      cs2030s.fp.Actually.<Integer>ok(0).transform(inv).toString(),
      "[java.lang.ArithmeticException] / by zero"
    );
    we.expect(
      "Actually.ok(0).transform(inc)",
      cs2030s.fp.Actually.ok(0).transform(inc).toString(),
      "<1>"
    );
    we.expect(
      "Actually.ok(0).transform(inv)",
      cs2030s.fp.Actually.ok(0).transform(inv).toString(),
      "[java.lang.ArithmeticException] / by zero"
    );
    
    we.expect(
      "Actually.<Integer>ok(0).transform(incNum)",
      cs2030s.fp.Actually.<Integer>ok(0).transform(incNum).toString(),
      "<1>"
    );
    we.expect(
      "Actually.<Integer>ok(0).transform(invNum)",
      cs2030s.fp.Actually.<Integer>ok(0).transform(invNum).toString(),
      "[java.lang.ArithmeticException] / by zero"
    );
    we.expect(
      "Actually.ok(0).transform(incNum)",
      cs2030s.fp.Actually.ok(0).transform(incNum).toString(),
      "<1>"
    );
    we.expect(
      "Actually.ok(0).transform(invNum)",
      cs2030s.fp.Actually.ok(0).transform(invNum).toString(),
      "[java.lang.ArithmeticException] / by zero"
    );
  }
}