import cs2030s.fp.Constant;
import cs2030s.fp.Immutator;
import cs2030s.fp.Lazy;
import cs2030s.fp.Memo;

public class Test3 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    
    Constant<String> password = () -> "123456";
    Lazy<String> lazy = Lazy.from(password);
    
    we.expect(
      "lazy",
      lazy.toString(),
      "123456"
    );
    we.expect(
      "lazy.transform(str -> str.substring(0, 1))",
      lazy.transform(str -> str.substring(0, 1)).toString(),
      "1"
    );
    
    Memo<String> memo = Memo.from(password);
    we.expect(
      "memo",
      memo.toString(),
      "?"
    );
    we.expect(
      "memo.transform(str -> str.substring(0, 1))",
      memo.transform(str -> str.substring(0, 1)).toString(),
      "?"
    );
    we.expect(
      "memo.transform(str -> str.substring(0, 1)).get()",
      memo.transform(str -> str.substring(0, 1)).get().toString(),
      "1"
    );
    we.expect(
      "memo",
      memo.toString(),
      "123456"
    );
    
    Immutator<Integer, String> len = str -> {
      System.out.println("length");
      return str.length();
    };
    Lazy<Integer> lazyLen = lazy.transform(len);
    we.expect(
      "lazyLen // warning: we do not check print output",
      lazyLen.toString(),
      "6"
    );
    we.expect(
      "lazyLen.get() // warning: we do not check print output",
      lazyLen.get(),
      6
    );
    we.expect(
      "lazyLen.get() // warning: we do not check print output",
      lazyLen.get(),
      6
    );
    
    Memo<Integer> memoLen = memo.transform(len);
    we.expect(
      "memoLen // warning: we do not check print output",
      memoLen.toString(),
      "?"
    );
    we.expect(
      "memoLen.get() // warning: we do not check print output",
      memoLen.get(),
      6
    );
    we.expect(
      "memoLen.get() // warning: we do not check print output",
      memoLen.get(),
      6
    );
    
    Memo<Integer> step1 = Memo.from(1010);
    Memo<Integer> step2 = step1.transform(i -> i * 2);
    Memo<Integer> step3 = step2.next(i -> Memo.from(i + 10));
    we.expect(
      "step1",
      step1.toString(),
      "1010"
    );
    we.expect(
      "step2",
      step2.toString(),
      "?"
    );
    we.expect(
      "step3",
      step3.toString(),
      "?"
    );
    we.expect(
      "step3.get()",
      step3.get(),
      2030
    );
    we.expect(
      "step2",
      step2.toString(),
      "2020"
    );
    we.expect(
      "step1",
      step1.toString(),
      "1010"
    );
    
    Memo<Integer> noErr = Memo.from(0);
    Memo<Integer> err = noErr.transform(x -> 1/x);
    try {
      we.expect(
        "err.get()",
        err.get(),
        ""
      );
    } catch(ArithmeticException ae) {
      we.expect(
        "err.get()",
        ae.getMessage(),
        "/ by zero"
      );
    }
  }
}