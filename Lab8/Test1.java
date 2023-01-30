import cs2030s.fp.InfiniteList;
import cs2030s.fp.Immutator;
import java.util.ArrayList;
import java.util.List;

class Test1 {
  public static void main(String[] args) {

    CS2030STest i = new CS2030STest();

    i.expectReturn(
        "InfiniteList.generate(() -> 1).toString()",
        () -> InfiniteList.generate(() -> 1).toString(), "[? ?]");

    i.expectReturn(
        "InfiniteList.generate(() -> 1).head()",
        () -> InfiniteList.generate(() -> 1).head(), 1);

    i.expectReturn(
        "InfiniteList.generate(() -> null).tail().head()",
        () -> InfiniteList.generate(() -> null).tail().head(), null);

    i.expectReturn(
        "InfiniteList.iterate(\"A\", x -> x + \"Z\").head()",
        () -> InfiniteList.iterate("A", x -> x + "Z").head(), "A");

    i.expectReturn(
        "InfiniteList.iterate(\"A\", x -> x + \"Z\").tail().head()",
        () -> InfiniteList.iterate("A", x -> x + "Z").tail().head(), "AZ");

    i.expectReturn(
        "InfiniteList.iterate(\"A\", x -> x + \"Z\").tail().tail().head()",
        () -> InfiniteList.iterate("A", x -> x + "Z").tail().tail().head(), "AZZ");

    List<Integer> evalHistory = new ArrayList<>();
    Immutator<Integer, Integer> op = x -> { 
      evalHistory.add(x); 
      return x + 1; 
    };

    InfiniteList<Integer> numbers = InfiniteList.iterate(1, op);
    numbers.head();
    i.expectReturn(
        "InfiniteList<Integer> numbers = InfiniteList.iterate(1, x -> x + 1);\n" +
        "numbers.toString()",
        () -> numbers.toString(), "[<1> ?]");
    i.expect("numbers.head() causes zero evaluation of x -> x + 1)",
        evalHistory, List.of());
    i.expectReturn("numbers.toString())",
        () -> numbers.toString(), "[<1> ?]");
    numbers.tail().head();
    i.expect("numbers.tail().head() causes one evaluation of x -> x + 1",
        evalHistory, List.of(1));
    i.expectReturn("numbers.toString())",
        () -> numbers.toString(), "[<1> [<2> ?]]");
    numbers.tail().head();
    i.expect("numbers.tail().head() (again) causes zero evaluation of x -> x + 1",
        evalHistory, List.of(1));
    i.expectReturn("numbers.toString())",
        () -> numbers.toString(), "[<1> [<2> ?]]");
    numbers.tail().tail().head();
    i.expect("numbers.tail().tail().head() causes one evaluation of x -> x + 1",
        evalHistory, List.of(1, 2));
    i.expectReturn("numbers.toString())",
        () -> numbers.toString(), "[<1> [<2> [<3> ?]]]");
    numbers.tail().head();
    i.expect("numbers.tail().head() (again) causes zero evaluation of x -> x + 1",
        evalHistory, List.of(1, 2));
    i.expectReturn("numbers.toString())",
        () -> numbers.toString(), "[<1> [<2> [<3> ?]]]");

    InfiniteList<Integer> zeros = InfiniteList.generate(() -> { 
      evalHistory.add(0); 
      return 0; 
    });
    evalHistory.retainAll(List.of());
    i.expect("InfiniteList<Integer> zeros = InfiniteList.generate(() -> 0)\n" +
        "zeros.toString() returns [? ?]",
        () -> zeros.toString(), "[? ?]");

    zeros.head();
    i.expect("zeros.head() causes one evaluation of () -> 0",
        evalHistory, List.of(0));
    i.expect("zeros.toString() returns [<0> ?]",
        () -> zeros.toString(), "[<0> ?]");

    zeros.tail().head();
    i.expect("zeros.tail().head() causes one evaluation of () -> 0)",
        evalHistory, List.of(0, 0));
    i.expect("zeros.toString() returns [<0> [[0 ?]]]",
        () -> zeros.toString(), "[<0> [<0> ?]]");

    zeros.head();
    i.expect("zeros.head() (again) causes zero evaluation of () -> 0",
        evalHistory, List.of(0, 0));
    i.expect("zeros.toString() returns [<0> [<0> ?]]",
        () -> zeros.toString(), "[<0> [<0> ?]]");

    zeros.tail().head();
    i.expect("zeros.tail().head() causes zero evaluation of () -> 0",
        evalHistory, List.of(0, 0));
    i.expect("zeros.toString() returns [<0> [<0> ?]]",
        () -> zeros.toString(), "[<0> [<0> ?]]");
        
    zeros.tail().tail().head();
    i.expect("zeros.tail().tail().head() causes one more evaluation of () -> 0",
        evalHistory, List.of(0, 0, 0));
    i.expect("zeros.toString() returns [<0> [<0> [<0> ?]]]",
        () -> zeros.toString(), "[<0> [<0> [<0> ?]]]");

    zeros.tail().head();
    i.expect("zeros.tail().head() (again) causes zero evaluation of () -> 0",
        evalHistory, List.of(0, 0, 0));
    i.expect("zeros.toString() returns [<0> [<0> [<0> ?]]]",
        () -> zeros.toString(), "[<0> [<0> [<0> ?]]]");
  }
}
