import cs2030s.fp.Constant;
import cs2030s.fp.Memo;

public class Test5 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    
    Constant<Boolean> t = new Constant<>() {
      public Boolean init() {
        return true;
      }
    };
    Constant<Boolean> f = new Constant<>() {
      public Boolean init() {
        String res = "";
        for (int i=0; i<100000; i++) {
          res += i;
        }
        return false;
      }
    };
    Cond cond = new And(new Or(new Bool(t), new Bool(f)), new Not(new Not(new Bool(t))));
    
    we.expect(
      "cond",
      cond.toString(),
      "((? | ?) & !(!(?)))"
    );
    we.expect(
      "cond.neg()",
      cond.neg().toString(),
      "((!(?) & !(?)) | !(?))"
    );
    we.expect(
      "cond.neg().neg()",
      cond.neg().neg().toString(),
      "((? | ?) & ?)"
    );
    we.expect(
      "cond.eval()",
      cond.eval(),
      true
    );
    we.expect(
      "cond.neg()",
      cond.neg().toString(),
      "((!(t) & !(?)) | !(t))"
    );
    we.expect(
      "cond.neg().neg()",
      cond.neg().neg().toString(),
      "((t | ?) & t)"
    );
  }
}