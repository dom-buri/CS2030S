import cs2030s.fp.Combiner;
import cs2030s.fp.Memo;

public class Test4 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    
    Combiner<String, Integer, Integer> concat = (x, y) -> {
      System.out.println("combine");
      return x.toString() + y.toString();
    };
    
    Memo<Integer> twenty, thirty, modInt;
    twenty = Memo.from(() -> 20);
    thirty = Memo.from(() -> 30);
    modInt = twenty.combine(thirty, (x, y) -> x * 100 + y);
    Memo<String> modStr = twenty.combine(thirty, concat);
    
    we.expect(
      "twenty",
      twenty.toString(),
      "?"
    );
    we.expect(
      "thirty",
      thirty.toString(),
      "?"
    );
    we.expect(
      "modInt",
      modInt.toString(),
      "?"
    );
    we.expect(
      "modStr",
      modStr.toString(),
      "?"
    );
    
    we.expect(
      "modStr.get()",
      modStr.get().toString(),
      "2030"
    );
    
    we.expect(
      "twenty",
      twenty.toString(),
      "20"
    );
    we.expect(
      "thirty",
      thirty.toString(),
      "30"
    );
    we.expect(
      "modInt",
      modInt.toString(),
      "?"
    );
    
    Combiner<String, Integer, Double> comb = (x, y) -> x.toString() + " + " + y.toString();
    Memo<String> s = modInt.combine(Memo.from(0.1), comb);
    we.expect(
      "s",
      s.toString(),
      "?"
    );
    we.expect(
      "s.get()",
      s.get(),
      "2030 + 0.1"
    );
    we.expect(
      "modInt",
      modInt.toString(),
      "2030"
    );
    
    Memo<Integer> x1 = Memo.from(1);
    for (int i = 0; i < 10; i ++) {
      final Memo<Integer> y1 = x1; // final just to ensure it is unchanged
      final int j = i;
      x1 = Memo.from(() -> { System.out.println(j); return y1.get() + y1.get(); });
    }
    we.expect(
      "x.get()",
      x1.get(),
      1024
    );
  }
}