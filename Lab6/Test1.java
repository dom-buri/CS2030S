import cs2030s.fp.Constant;
import cs2030s.fp.Lazy;

public class Test1 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    
    Lazy<Integer> mod1 = Lazy.from(2030);
    we.expect(
      "Lazy<Integer> mod1 = Lazy.from(2030)",
      mod1.toString(),
      "2030"
    );
    
    Lazy<String> mod2 = Lazy.from(() -> "CS2030S");
    we.expect(
      "Lazy<String> mod2 = Lazy.from(\"CS2030S\")",
      mod2.toString(),
      "CS2030S"
    );
    
    Lazy<String> hello = Lazy.from(() -> {
      System.out.println("world!");
      return "hello";
    });
    we.expect(
      "hello.get() // warning: we do not check print output",
      hello.get().toString(),
      "hello"
    );
    we.expect(
      "hello.get() // warning: we do not check print output",
      hello.get().toString(),
      "hello"
    );
  }
}