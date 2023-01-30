import cs2030s.fp.Immutator;
import cs2030s.fp.Memo;

public class Test1 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    
    int[] eval = new int[]{0};
    Immutator<Integer, Integer> incr = x -> {
      eval[0] += 1;
      return x + 1;
    };
    
    MemoList<Integer> l = MemoList.generate(4, 0, incr);
    
    we.expect(
      "An initial MemoList only has a single evaluated element",
      l.toString(),
      "[0, ?, ?, ?]"
    );
    
    l.indexOf(2);
    we.expect(
      "Looking for 2 causes 2 evaluations",
      eval[0],
      2
    );
    
    l.indexOf(1);
    we.expect(
      "Looking for 1 does not need any more evaluation",
      eval[0],
      2
    );
    
    l.get(1);
    we.expect(
      "Retrieving index 1 does not need any more evaluation",
      eval[0],
      2
    );
    
    l.get(3);
    we.expect(
      "Retrieving index 3 causes one more evaluation",
      eval[0],
      3
    );
  }
}