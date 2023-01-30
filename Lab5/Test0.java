import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class Test0 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    
    we.expectCompile("Actually<Object> a = new Actually<>() should not compile",
      "cs2030s.fp.Actually<Object> a = new cs2030s.fp.Actually<>();", false);
    we.expectCompile("Actually.Success<Object> s should not compile",
      "cs2030s.fp.Actually.Success<Object> s;", false);
    we.expectCompile("Actually.Failure f should not compile",
      "cs2030s.fp.Actually.Failure f;", false);
      
    we.expectCompile("Actually<String> success = Actually.ok(\"success\") should compile",
      "cs2030s.fp.Actually<String> success = cs2030s.fp.Actually.ok(\"success\");", true);
    we.expectCompile("Actually<Integer> none = Actually.ok(null) should compile",
      "cs2030s.fp.Actually<Integer> none = cs2030s.fp.Actually.ok(null);", true);
    we.expectCompile("Actually<Integer> four = Actually.ok(4) should compile",
      "cs2030s.fp.Actually<Integer> four = cs2030s.fp.Actually.ok(4);", true);
    we.expectCompile("Actually<Object> div0 = Actually.err(new ArithmeticException(\"Divide by 0\")) should compile",
      "cs2030s.fp.Actually<Object> div0 = cs2030s.fp.Actually.err(new ArithmeticException(\"Divide by 0\"));", true);
  }
}