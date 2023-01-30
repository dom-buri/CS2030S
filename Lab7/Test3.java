import cs2030s.fp.Immutator;
import cs2030s.fp.Memo;

public class Test3 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    
    int[] _incr = new int[]{0};
    Immutator<Integer, Integer> incr = x -> {
      _incr[0] += 1;
      return x + 1;
    };
    int[] _dbl = new int[]{0};
    Immutator<Integer, Integer> dbl = x -> {
      _dbl[0] += 1;
      return x + x;
    };
    
    MemoList<Integer> nat = MemoList.generate(10, 0, incr);
    we.expect(
      "An initial natural number only has a single evaluated element",
      nat.toString(),
      "[0, ?, ?, ?, ?, ?, ?, ?, ?, ?]"
    );
    
    MemoList<Integer> even = nat.map(dbl);
    we.expect(
      "An initial even number has no evaluated element",
      even.toString(),
      "[?, ?, ?, ?, ?, ?, ?, ?, ?, ?]"
    );
    
    nat.indexOf(2);
    we.expect(
      "Looking for 2 on natural number causes 2 evaluations on increments ...",
      _incr[0],
      2
    );
    we.expect(
      "... and causes 0 evaluations on double",
      _dbl[0],
      0
    );
    
    even.indexOf(6);
    we.expect(
      "Looking for 6 on even number causes 1 more evaluation on increment ...",
      _incr[0],
      3
    );
    we.expect(
      "... and causes 4 evaluation on double",
      _dbl[0],
      4
    );
    
    MemoList<Integer> odd = even.map(incr);
    odd.get(8);
    we.expect(
      "Retrieving index 8 on odd number causes 5 more evaluation on increment ...",
      _incr[0],
      9
    );
    we.expect(
      "... and causes 1 more evaluation on double",
      _dbl[0],
      5
    );
  }
}