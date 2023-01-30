import cs2030s.fp.Immutator;
import cs2030s.fp.Combiner;
import cs2030s.fp.Memo;

public class Test5 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    
    class A {
      private int x;
      public A(int x) {
        this.x = x;
      }
      public int getX() {
        return this.x;
      }
      @Override
      public String toString() {
        return "A:" + this.x;
      }
    }
    class B extends A {
      public B(int x) {
        super(x);
      }
      @Override
      public String toString() {
        return "B:" + super.toString();
      }
    }
    class C extends B {
      public C(int x) {
        super(x);
      }
      @Override
      public String toString() {
        return "C:" + super.toString();
      }
    }
    
    Immutator<C, A> incr = x -> new C(x.getX() + 1);
    Combiner<C, A, A> fib = (x, y) -> new C(x.getX() + y.getX());
    Immutator<MemoList<B>, A> dbl  = x -> MemoList.generate(x.getX(), new C(x.getX()), y -> new C(y.getX() + 1));
    MemoList<B> bList1 = MemoList.generate(8, new C(0), incr);
    we.expect(
      "PECS is followed on generate (Immutator)",
      bList1.toString(),
      "[C:B:A:0, ?, ?, ?, ?, ?, ?, ?]"
    );
    
    MemoList<B> bList2 = MemoList.generate(8, new C(0), new C(1), fib);
    we.expect(
      "PECS is followed on generate (Combiner)",
      bList2.toString(),
      "[C:B:A:0, C:B:A:1, ?, ?, ?, ?, ?, ?]"
    );
    
    MemoList<B> bList3 = bList1.map(incr);
    we.expect(
      "PECS is followed on map",
      bList3.toString(),
      "[?, ?, ?, ?, ?, ?, ?, ?]"
    );
    
    MemoList<B> bList4 = bList1.flatMap(dbl);
    we.expect(
      "PECS is followed on flatMap",
      bList4.toString(),
      "[C:B:A:1, C:B:A:2, ?, C:B:A:3, ?, ?, C:B:A:4, ?, ?, ?, C:B:A:5, ?, ?, ?, ?, C:B:A:6, ?, ?, ?, ?, ?, C:B:A:7, ?, ?, ?, ?, ?, ?]"
    );
  }
}