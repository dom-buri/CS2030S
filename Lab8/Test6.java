import cs2030s.fp.InfiniteList;

class Test6 {
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    InfiniteList<Integer> numbers = InfiniteList.iterate(0, x -> x + 1);
    i.expectReturn(
        "InfiniteList.end().reduce(0, (x, y) -> x + y)",
        () -> InfiniteList.<Integer>end().reduce(0, (x, y) -> x + y), 0);
    i.expectReturn(
        "InfiniteList.iterate(0, x -> x + 1).limit(5).reduce(0, (x, y) -> x + y)",
        () -> numbers.limit(5).reduce(0, (x, y) -> x + y), 10);
    i.expectReturn(
        "InfiniteList.iterate(0, x -> x + 1).limit(0).reduce(0, (x, y) -> x + y)",
        () -> InfiniteList.iterate(0, x -> x + 1).limit(0).reduce(0, (x, y) -> x + y), 0);
    i.expectReturn(
        "InfiniteList.iterate(1, x -> x + 1).map(x -> x * x).limit(5).reduce(1, (x, y) -> x * y)",
        () -> InfiniteList.iterate(1, x -> x + 1).map(x -> x * x).limit(5)
        .reduce(1, (x, y) -> x * y),
        14400);

    i.expectReturn(
        "InfiniteList.end().count()",
        () -> InfiniteList.end().count(), 0L);
    i.expectReturn(
        "InfiniteList.iterate(0, x -> x + 1).limit(0).count()",
        () -> numbers.limit(0).count(), 0L);
    i.expectReturn(
        "InfiniteList.iterate(0, x -> x + 1).limit(1).count()",
        () -> numbers.limit(1).count(), 1L);
    i.expectReturn(
        "InfiniteList.iterate(0, x -> x + 1).filter(x -> x % 2 == 1).limit(10).count()",
        () -> numbers.filter(x -> x % 2 == 1).limit(10).count(), 10L);
    i.expectReturn(
        "InfiniteList.iterate(0, x -> x + 1).limit(10).filter(x -> x % 2 == 1).count()",
        () -> numbers.limit(10).filter(x -> x % 2 == 1).count(), 5L);
    i.expectReturn(
        "InfiniteList.iterate(0, x -> x + 1).takeWhile(x -> x < 10).count()",
        () -> numbers.takeWhile(x -> x < 10).count(), 10L);
    i.expectReturn(
        "InfiniteList.iterate(0, x -> x + 1).takeWhile(x -> x < 10).filter(x -> x % 2 == 0)" +
        ".count()",
        () -> numbers.takeWhile(x -> x < 10).filter(x -> x % 2 == 0).count(), 5L);
  }
}
