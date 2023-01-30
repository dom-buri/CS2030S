import cs2030s.fp.InfiniteList;
import cs2030s.fp.Constant;
import cs2030s.fp.Immutator;
import java.util.ArrayList;
import java.util.List;

class Test2 {
  public static void main(String[] args) {

    CS2030STest i = new CS2030STest();
    i.expectReturn(
        "InfiniteList.generate(() -> 1).map(x -> x * 2).toString()",
        () -> InfiniteList.generate(() -> 1).map(x -> x * 2).toString(), 
        "[? ?]");
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).toString()",
        () -> InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).toString(), 
        "[? ?]");

    i.expectReturn(
        "InfiniteList.generate(() -> 1).map(x -> x * 2).head()",
        () -> InfiniteList.generate(() -> 1).map(x -> x * 2).head(), 2);
    i.expectReturn(
        "InfiniteList.generate(() -> 1).map(x -> x * 2).tail().head()",
        () -> InfiniteList.generate(() -> 1).map(x -> x * 2).tail().head(), 2);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).head()",
        () -> InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).head(), 2);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).tail().head()",
        () -> InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).tail().head(), 4);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).map(x -> x - 1).head()",
        () -> InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).map(x -> x - 1).head(), 1);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).map(x -> x - 1).tail().head()",
        () -> InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).map(x -> x - 1).tail().head(), 3);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).map(x -> x % 2 == 0 ? null : x).tail().head()",
        () -> InfiniteList.iterate(1, x -> x + 1).map(x -> x % 2 == 0 ? null : x).tail().head(),
        null);

    List<Integer> doublerHistory = new ArrayList<>();
    List<Integer> generateHistory = new ArrayList<>();
    Constant<Integer> generator = () -> { 
      generateHistory.add(1); 
      return 1; 
    };
    Immutator<Integer, Integer> doubler = x -> { 
      doublerHistory.add(x); 
      return x * 2; 
    };

    i.expect("InfiniteList.generate(() -> 1).map(x -> x * 2).tail().head()\n" +
        " ..returns 2",
        () -> InfiniteList.generate(generator).map(doubler).tail().head(), 2);
    i.expect(" ..causes two evals of () -> 1",
        generateHistory, List.of(1, 1));
    i.expect(" ..causes two evals of x -> x * 2",
        doublerHistory, List.of(1, 1));

    generateHistory.retainAll(List.of());
    doublerHistory.retainAll(List.of());
    InfiniteList<Integer> ones = InfiniteList.generate(generator);
    InfiniteList<Integer> twos = ones.map(doubler);
    twos.tail().head();
    i.expect("InfiniteList<Integer> ones = InfiniteList.generate(() -> 1)\n" + 
        "InfiniteList<Integer> twos = ones.map(x -> x * 2)\n" + 
        "After twos.tail().head()\n" +
        " ..ones.toString() returns [<1> [<1> ?]]",
        () -> ones.toString(), "[<1> [<1> ?]]");
    i.expect(" ..twos.toString() returns [<2> [<2> ?]]",
        () -> twos.toString(), "[<2> [<2> ?]]");
    twos.head();
    i.expect(" ..calling twos.head() again\n" + 
        " ....causes zero evaluation of () -> 1",
        generateHistory, List.of(1, 1));
    i.expect(" ....causes zero evaluation of x -> x * 2",
        doublerHistory, List.of(1, 1));
    twos.tail().head();
    i.expect(" ..calling twos.tail().head() again\n" +
        " ....causes zero evaluation of () -> 1",
        generateHistory, List.of(1, 1));
    i.expect(" ....causes zero evaluation of x -> x * 2",
        doublerHistory, List.of(1, 1));
  }
}
