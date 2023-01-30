import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Test5 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    
    cs2030s.fp.Immutator<cs2030s.fp.Actually<Integer>, Integer> half = new cs2030s.fp.Immutator<>() {
      public cs2030s.fp.Actually<Integer> invoke(Integer p) {
        if (p % 2 == 0) {
          return cs2030s.fp.Actually.<Integer>ok(p / 2);
        } else {
          return cs2030s.fp.Actually.<Integer>err(new Exception("odd number"));
         // return cs2030s.fp.Actually.<Integer>ok(p);
        }
      }
    };
    cs2030s.fp.Immutator<cs2030s.fp.Actually<Integer>, Integer> inc = new cs2030s.fp.Immutator<>() {
      public cs2030s.fp.Actually<Integer> invoke(Integer p) {
        return cs2030s.fp.Actually.<Integer>ok(p + 1);
      }
    };
    cs2030s.fp.Immutator<cs2030s.fp.Actually<Integer>, Integer> make = new cs2030s.fp.Immutator<>() {
      public cs2030s.fp.Actually<Integer> invoke(Integer p) {
        return cs2030s.fp.Actually.<Integer>ok(p);
      }
    };
    cs2030s.fp.Constant<Integer> zero = new cs2030s.fp.Constant<>() {
      public Integer init() {
        return 0;
      }
    };
    
    we.expect(
        "make.invoke(0).next(inc).next(inc).next(half)",
        make.invoke(0).next(inc).next(inc).next(half).toString(),
        "<1>"
    );
    we.expect(
        "make.invoke(0).next(inc).next(half).next(inc)",
        make.invoke(0).next(inc).next(half).next(inc).toString(),
        "[java.lang.Exception] odd number"
    );
    
    we.expect(
        "make.invoke(0).next(inc).next(inc).next(half).except(zero)",
        make.invoke(0).next(inc).next(inc).next(half).except(zero).toString(),
        "1"
    );
    we.expect(
        "make.invoke(0).next(inc).next(half).next(inc).except(zero)",
        make.invoke(0).next(inc).next(half).next(inc).except(zero).toString(),
        "0"
    );
  }
}
