import cs2030s.fp.InfiniteList;
import cs2030s.fp.Immutator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Test4 {
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).isEnd()",
        () -> InfiniteList.iterate(1, x -> x + 1).isEnd(), false);
    i.expectReturn(
        "InfiniteList.generate(() -> 2).isEnd()",
        () -> InfiniteList.generate(() -> 2).isEnd(), false);
    i.expectReturn(
        "InfiniteList.generate(() -> 2).filter(x -> x % 3 == 0).isEnd()",
        () -> InfiniteList.generate(() -> 2).filter(x -> x % 3 == 0).isEnd(), false);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).map(x -> 2).isEnd()",
        () -> InfiniteList.iterate(1, x -> x + 1).map(x -> 2).isEnd(), false);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).isEnd()",
        () -> InfiniteList.iterate(1, x -> x + 1).map(x -> 2).isEnd(), false);

    i.expectReturn("InfiniteList.end().isEnd()",
        () -> InfiniteList.end().isEnd(), true);
    i.expectReturn("InfiniteList.end().map(x -> 2).isEnd()",
        () -> InfiniteList.end().map(x -> 2).isEnd(), true);
    i.expectReturn("InfiniteList.end().filter(x -> true).isEnd()",
        () -> InfiniteList.end().filter(x -> true).isEnd(), true);
    i.expectReturn("InfiniteList.end().filter(x -> false).isEnd()",
        () -> InfiniteList.end().filter(x -> false).isEnd(), true);

    i.expectReturn(
        "InfiniteList.end().limit(4).isEnd()",
        () -> InfiniteList.end().limit(4).isEnd(), true);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).limit(0).isEnd()",
        () -> InfiniteList.iterate(1, x -> x + 1).limit(0).isEnd(), true);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).limit(0).isEnd()",
        () -> InfiniteList.iterate(1, x -> x + 1).limit(0).isEnd(), true);

    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).limit(1).isEnd()",
        () -> InfiniteList.iterate(1, x -> x + 1).limit(1).isEnd(), false);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).limit(10).isEnd()",
        () -> InfiniteList.iterate(1, x -> x + 1).limit(10).isEnd(), false);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).limit(-1).isEnd()",
        () -> InfiniteList.iterate(1, x -> x + 1).limit(-1).isEnd(), true);

    List<Integer> incrHistory = new ArrayList<>();
    Immutator<Integer, Integer> incr = x -> {
      incrHistory.add(x);
      return x + 1;
    };
    InfiniteList.iterate(1, incr).limit(0).isEnd();
    i.expect("InfiniteList.iterate(1, x -> x + 1).limit(0).isEnd() " + 
        "causes zero evaluation of x -> x + 1", 
        incrHistory, List.of());
    InfiniteList.iterate(1, incr).limit(1).isEnd();
    i.expect("InfiniteList.iterate(1, x -> x + 1).limit(1).isEnd() " + 
        "causes zero evaluation of x -> x + 1", 
        incrHistory, List.of());
    InfiniteList.iterate(1, incr).limit(10).isEnd();
    i.expect("InfiniteList.iterate(1, x -> x + 1).limit(10).isEnd() " + 
        "causes zero evaluation of x -> x + 1", 
        incrHistory, List.of());

    i.expectReturn("InfiniteList.generate(() -> 1).limit(4).toString()",
        () -> InfiniteList.generate(() -> 1).limit(4).toString(), 
        "[? ?]");
    i.expectReturn("InfiniteList.iterate(1, x -> x + 1).limit(4).toString()",
        () -> InfiniteList.iterate(1, incr).limit(4).toString(), 
        "[<1> ?]");
    i.expectException("InfiniteList.iterate(1, x -> x + 1).limit(0).head()",
        () -> InfiniteList.iterate(1, incr).limit(0).head(), 
        new java.util.NoSuchElementException());
    i.expectReturn("InfiniteList.iterate(1, x -> x + 1).limit(1).head()",
        () -> InfiniteList.iterate(1, incr).limit(1).head(), 1);
    i.expectReturn("InfiniteList.iterate(1, x -> x + 1).limit(4).head()",
        () -> InfiniteList.iterate(1, incr).limit(4).head(), 1);
    i.expectException("InfiniteList.iterate(1, x -> x + 1).limit(1).tail().head()",
        () -> InfiniteList.iterate(1, incr).limit(1).tail().head(), 
        new java.util.NoSuchElementException());
    i.expectReturn("InfiniteList.iterate(1, x -> x + 1).limit(4).tail().tail().head()",
        () -> InfiniteList.iterate(1, incr).limit(4).tail().tail().head(), 3);
    i.expectException("InfiniteList.iterate(1, x -> x + 1).limit(4).limit(1).tail().head()",
        () -> InfiniteList.iterate(1, incr).limit(4).limit(1).tail().head(), 
        new java.util.NoSuchElementException());
    i.expectException("InfiniteList.iterate(1, x -> x + 1).limit(1).limit(4).tail().head()", 
        () -> InfiniteList.iterate(1, incr).limit(1).limit(4).tail().head(), 
        new java.util.NoSuchElementException());

    Immutator<Boolean, Integer> isEven = x -> (x % 2 == 0);

    i.expectException(
        "InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).limit(0).head()",
        () -> InfiniteList.iterate(1, incr).filter(isEven).limit(0).head(), 
        new java.util.NoSuchElementException());
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).limit(1).head()",
        () -> InfiniteList.iterate(1, incr).filter(isEven).limit(1).head(), 2);
    i.expectException(
        "InfiniteList.iterate(1, x -> x + 1).limit(1).filter(x -> x % 2 == 0).head()",
        () -> InfiniteList.iterate(1, incr).limit(1).filter(isEven).head(), 
        new java.util.NoSuchElementException());
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).limit(2).filter(x -> x % 2 == 0).head()",
        () -> InfiniteList.iterate(1, incr).limit(2).filter(isEven).head(), 2);

    i.expectReturn(
        "InfiniteList.iterate(\"A\", s -> s + \"Z\").limit(2).map(s -> s.length()).head()",
        () -> InfiniteList.iterate("A", s -> s + "Z").limit(2).map(s -> s.length()).head(), 1);
    i.expectReturn(
        "InfiniteList.iterate(\"A\", s -> s + \"Z\").limit(2).map(s -> s.length()).tail().head()",
        () -> InfiniteList.iterate("A", s -> s + "Z").limit(2).map(s -> s.length())
        .tail().head(), 2);
    i.expectException(
        "InfiniteList.iterate(\"A\", s -> s + \"Z\").limit(2).map(s -> s.length()).tail().tail()" +
        ".head()",
        () -> InfiniteList.iterate("A", s -> s + "Z").limit(2).map(s -> s.length()).tail().tail()
        .head(),
        new java.util.NoSuchElementException());

    i.expectReturn(
        "InfiniteList.iterate(\"A\", s -> s + \"Z\").map(s -> s.length()).limit(2).head()",
        () -> InfiniteList.iterate("A", s -> s + "Z").map(s -> s.length()).head(), 1);
    i.expectReturn(
        "InfiniteList.iterate(\"A\", s -> s + \"Z\").map(s -> s.length()).limit(2).tail().head()",
        () -> InfiniteList.iterate("A", s -> s + "Z").map(s -> s.length()).tail().head(), 2);
    i.expectException(
        "InfiniteList.iterate(\"A\", s -> s + \"Z\").map(s -> s.length()).limit(2).tail().tail()" +
        ".head()",
        () -> InfiniteList.iterate("A", s -> s + "Z").map(s -> s.length()).limit(2).tail().tail()
        .head(), 
        new java.util.NoSuchElementException());

    i.expectReturn(
        "InfiniteList.<String>end().toList()",
        () -> InfiniteList.<String>end().toList(), List.of());
    i.expectReturn(
        "InfiniteList.iterate(\"A\", s -> s + \"Z\").map(s -> s.length()).limit(2).toList()",
        () -> InfiniteList.iterate("A", s -> s + "Z").map(s -> s.length()).limit(2).toList(),
        List.of(1, 2));
    i.expectReturn(
        "InfiniteList.iterate(\"A\", s -> s + \"Z\").limit(2).map(s -> s.length()).toList()",
        () -> InfiniteList.iterate("A", s -> s + "Z").limit(2).map(s -> s.length()).toList(),
        List.of(1, 2));
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).limit(2).filter(x -> x % 2 == 0).toList()",
        () -> InfiniteList.iterate(1, incr).limit(2).filter(isEven).toList(), List.of(2));
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).limit(2).toList()",
        () -> InfiniteList.iterate(1, incr).filter(isEven).limit(2).toList(), List.of(2, 4));

    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).limit(10).limit(3).toList()",
        () -> InfiniteList.iterate(1, x -> x + 1).limit(10).limit(3).toList(), List.of(1, 2, 3));
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).limit(3).limit(10).toList()",
        () -> InfiniteList.iterate(1, x -> x + 1).limit(3).limit(10).toList(), List.of(1, 2, 3));

    i.expectReturn(
        "InfiniteList.generate(() -> 4).limit(0).toList()",
        () -> InfiniteList.generate(() -> 4).limit(0).toList(), List.of());
    i.expectReturn(
        "InfiniteList.generate(() -> 4).limit(2).toList()",
        () -> InfiniteList.generate(() -> 4).limit(2).toList(), List.of(4, 4));

    i.expectReturn(
        "InfiniteList.iterate(0, x -> x + 1).filter(x -> x > 10).map(x -> x.hashCode() % 30)" + 
        ".filter(x -> x < 20).limit(5).toList()",
        () -> InfiniteList.iterate(0, x -> x + 1).filter(x -> x > 10).map(x -> x.hashCode() % 30)
        .filter(x -> x < 20).limit(5).toList(),
        List.of(11, 12, 13, 14, 15));

    java.util.Random rng = new java.util.Random(1);
    i.expectReturn(
        "InfiniteList.generate(() -> rng.nextInt() % 100).filter(x -> x > 10).limit(4).toList()",
        () -> InfiniteList.generate(() -> rng.nextInt() % 100).filter(x -> x > 10).limit(4)
        .toList(),
        List.of(76, 95, 26, 69));

    i.expectReturn("InfiniteList.generate(() -> null).limit(4).limit(1).toList()",
        () -> InfiniteList.<Object>generate(() -> null).limit(4).limit(1).toList(), 
        Arrays.asList(new Object[] { null }));
    i.expectReturn("InfiniteList.generate(() -> null).limit(1).limit(4).toList()",
        () -> InfiniteList.<Object>generate(() -> null).limit(1).limit(4).toList(), 
        Arrays.asList(new Object[] { null }));
  }
}
