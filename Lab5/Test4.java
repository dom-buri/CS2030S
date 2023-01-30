import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class Test4 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    
    cs2030s.fp.Transformer<Integer,Integer> inc = new cs2030s.fp.Transformer<>() {
      public Integer invoke(Integer p) {
        return p+1;
      }
    };
    cs2030s.fp.Transformer<Integer,Integer> sqr = new cs2030s.fp.Transformer<>() {
      public Integer invoke(Integer p) {
        return p*p;
      }
    };
    
    cs2030s.fp.Transformer<Integer,Integer> sqrPlusOneA = sqr.before(inc);
    cs2030s.fp.Transformer<Integer,Integer> sqrPlusOneB = inc.after(sqr);
    cs2030s.fp.Transformer<Integer,Integer> plusOneSqrA = sqr.after(inc);
    cs2030s.fp.Transformer<Integer,Integer> plusOneSqrB = inc.before(sqr);
    
    we.expect(
      "sqrPlusOneA.invoke(2)",
      sqrPlusOneA.invoke(2).toString(),
      "5"
    );
    we.expect(
      "sqrPlusOneB.invoke(2)",
      sqrPlusOneA.invoke(2).toString(),
      "5"
    );
    we.expect(
      "plusOneSqrA.invoke(2)",
      plusOneSqrA.invoke(2).toString(),
      "9"
    );
    we.expect(
      "plusOneSqrB.invoke(2)",
      plusOneSqrB.invoke(2).toString(),
      "9"
    );
  }
}
