import cs2030s.fp.Immutator;
import cs2030s.fp.Memo;

public class Test4 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    
    int[] _dupl = new int[]{0};
    int[] _copy = new int[]{0};
    Immutator<MemoList<Integer>, Integer> dupl = x -> {
      _dupl[0] += 1;
      return MemoList.generate(x, x, n -> {
        _copy[0] += 1;
        return x;
      });
    };
    
    MemoList<Integer> nat = MemoList.generate(5, 1, x -> x + 1);
    we.expect(
      "An initial natural number only has a single evaluated element",
      nat.toString(),
      "[1, ?, ?, ?, ?]"
    );
    
    MemoList<Integer> superNat = nat.flatMap(dupl);
    we.expect(
      "An initial super natural number has 5 evaluated element",
      superNat.toString(),
      "[1, 2, ?, 3, ?, ?, 4, ?, ?, ?, 5, ?, ?, ?, ?]"
    );
    we.expect(
      "... and we check the number of evaluation on dupl",
      _dupl[0],
      5
    );
    
    we.expect(
      "... and there are 0 evaluation on copy",
      _copy[0],
      0
    );
    int[] _dbl = new int[]{0};
    Immutator<Integer, Integer> dbl = x -> {
      _dbl[0] += 1;
      return x * 2;
    };
    MemoList<Integer> superEven = superNat.map(dbl);
    we.expect(
      "An initial super even number has no evaluated element",
      superEven.toString(),
      "[?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?]"
    );
    we.expect(
      "... and we check the number of evaluation on dbl",
      _dbl[0],
      0
    );
    we.expect(
      "... and there are 0 additional evaluation on dupl",
      _dupl[0],
      5
    );
    we.expect(
      "... and there are still 0 evaluation on copy",
      _copy[0],
      0
    );
    
    superEven.get(12);
    we.expect(
      "Retrieving index 12 on super even number causes 1 evaluation on dbl ...",
      _dbl[0],
      1
    );
    we.expect(
      "... and causes 0 additional evaluations on dupl",
      _dupl[0],
      5
    );
    we.expect(
      "... and causes 2 evaluations on copy",
      _copy[0],
      2
    );
    
    we.expect(
      "Final super even number to contain 1 evaluated element",
      superEven.toString(),
      "[?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 10, ?, ?]"
    );
    we.expect(
      "... and final super natural number to contain 7 evaluated element",
      superNat.toString(),
      "[1, 2, ?, 3, ?, ?, 4, ?, ?, ?, 5, 5, 5, ?, ?]"
    );
    we.expect(
      "... and final natural number to contain 5 evaluated element",
      nat.toString(),
      "[1, 2, 3, 4, 5]"
    );
    
    MemoList<Integer> nat2 = MemoList.generate(5, 1, x -> x + 1);
    MemoList<MemoList<Integer>> nestNat2 = nat2.map(dupl);
    we.expect(
      "An initial nested natural number has no evaluated element",
      nestNat2.toString(),
      "[?, ?, ?, ?, ?]"
    );
    we.expect(
      "... and we check the number of evaluation on dupl",
      _dupl[0],
      5
    );
    
    nestNat2.get(2);
    we.expect(
      "Retrieving index 2 on nested natural number causes 1 evaluation on dupl ...",
      nestNat2.toString(),
      "[?, ?, [3, ?, ?], ?, ?]"
    );
    we.expect(
      "... and we check the number of evaluation on dupl",
      _dupl[0],
      6
    );
    
    for (int i=0; i<5; i++) {
      nestNat2.get(i);
    }
    we.expect(
      "nestNat2 to be a partially evaluated nested list",
      nestNat2.toString(),
      "[[1], [2, ?], [3, ?, ?], [4, ?, ?, ?], [5, ?, ?, ?, ?]]"
    );
    for (int i=0; i<5; i++) {
      for (int j=0; j<=i; j++) {
        nestNat2.get(i).get(j);
      }
    }
    we.expect(
      "nestNat2 to be a fully evaluated nested list",
      nestNat2.toString(),
      "[[1], [2, 2], [3, 3, 3], [4, 4, 4, 4], [5, 5, 5, 5, 5]]"
    );
  }
}