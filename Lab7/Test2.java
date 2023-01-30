import cs2030s.fp.Combiner;
import cs2030s.fp.Memo;

public class Test2 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    
    int[] eval = new int[]{0};
    Combiner<Integer, Integer, Integer> fib = (x, y) -> {
      eval[0] += 1;
      return x + y;
    };
    
    MemoList<Integer> l = MemoList.generate(8, 0, 1, fib);
    
    we.expect(
      "An initial MemoList only has the first two values evaluated",
      l.toString(),
      "[0, 1, ?, ?, ?, ?, ?, ?]"
    );
    
    l.indexOf(5);
    we.expect(
      "Looking for 5 causes 4 evaluations",
      eval[0],
      4
    );
    
    l.indexOf(3);
    we.expect(
      "Looking for 3 does not need any more evaluation",
      eval[0],
      4
    );
    
    l.get(4);
    we.expect(
      "Retrieving index 4 does not need any more evaluation",
      eval[0],
      4
    );
    
    l.get(6);
    we.expect(
      "Retrieving 6 causes one more evaluation",
      eval[0],
      5
    );
  }
}