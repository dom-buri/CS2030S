import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class Test1 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
      
    String none = null;
    
    we.expect(
      "Actually.err(new ArithmeticException(\"Err\")).equals(Actually.err(new Exception(\"Err\")))",
      cs2030s.fp.Actually.err(new ArithmeticException("Err")).equals(cs2030s.fp.Actually.err(new Exception("Err"))),
      true
    );
    we.expect(
      "Actually.err(new ArithmeticException(\"Err\")).equals(Actually.err(new Exception(\"Error\")))",
      cs2030s.fp.Actually.err(new ArithmeticException("Err")).equals(cs2030s.fp.Actually.err(new Exception("Error"))),
      false
    );
    we.expect(
      "Actually.err(new ArithmeticException(\"Err\")).equals(Actually.err(new Exception(null)))",
      cs2030s.fp.Actually.err(new ArithmeticException("Err")).equals(cs2030s.fp.Actually.err(new Exception(none))),
      false
    );
    we.expect(
      "Actually.err(new ArithmeticException(null)).equals(Actually.err(new Exception(null)))",
      cs2030s.fp.Actually.err(new ArithmeticException(none)).equals(cs2030s.fp.Actually.err(new Exception(none))),
      false
    );
    we.expect(
      "Actually.err(new ArithmeticException(\"Err\")).equals(Actually.ok(\"Err\"))",
      cs2030s.fp.Actually.err(new ArithmeticException("Err")).equals(cs2030s.fp.Actually.ok("Err")),
      false
    );
    
    we.expect(
      "Actually.ok(\"Err\").equals(Actually.ok(\"Err\"))",
      cs2030s.fp.Actually.ok("Err").equals(cs2030s.fp.Actually.ok("Err")),
      true
    );
    we.expect(
      "Actually.ok(\"Err\").equals(Actually.err(new Exception(\"Error\")))",
      cs2030s.fp.Actually.ok("Err").equals(cs2030s.fp.Actually.err(new Exception("Error"))),
      false
    );
    we.expect(
      "Actually.ok(\"Err\").equals(\"Err\")",
      cs2030s.fp.Actually.ok("Err").equals("Err"),
      false
    );
    
    we.expect(
      "Actually.ok(null).equals(Actually.ok(\"Err\"))",
      cs2030s.fp.Actually.ok(null).equals(cs2030s.fp.Actually.ok("Err")),
      false
    );
    we.expect(
      "Actually.ok(null).equals(Actually.ok(null))",
      cs2030s.fp.Actually.ok(null).equals(cs2030s.fp.Actually.ok(null)),
      true
    );
    we.expect(
      "Actually.ok(null).equals(\"Err\")",
      cs2030s.fp.Actually.ok(null).equals("Err"),
      false
    );
    we.expect(
      "Actually.ok(null).equals(null)",
      cs2030s.fp.Actually.ok(null).equals(null),
      false
    );
  }
}