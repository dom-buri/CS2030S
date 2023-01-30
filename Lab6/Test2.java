import cs2030s.fp.Constant;
import cs2030s.fp.Memo;

public class Test2 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    
    Memo<Integer> mod1 = Memo.from(2030);
    we.expect(
      "Memo<Integer> mod1 = Memo.from(2030)",
      mod1.toString(),
      "2030"
    );
    
    Memo<String> mod2 = Memo.from(() -> "CS2030S");
    we.expect(
      "Memo<String> mod2 = Memo.from(\"CS2030S\")",
      mod2.toString(),
      "?"
    );
    we.expect(
      "mod2.get()",
      mod2.get().toString(),
      "CS2030S"
    );
    
    Memo<String> hello = Memo.from(() -> {
      System.out.println("world!");
      return "hello";
    });
    we.expect(
      "hello.get() // warning: we do not check print output",
      hello.get(),
      "hello"
    );
    we.expect(
      "hello.get() // warning: we do not check print output",
      hello.get(),
      "hello"
    );
  }
}