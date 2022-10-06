class Test5 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    
    class Incr implements Immutator<Integer, Integer> {
      public Integer invoke(Integer t1) {
        return t1 + 1;
      }
    }

    class Length implements Immutator<Integer, String> {
      public Integer invoke(String t1) {
        return t1.length();
      }
    }
    
    we.expect("Probably.just(17).check(new IsModEq(3,2)) // 17 % 3 is equal to 2",
              Probably.just(17).check(new IsModEq(3, 2)).toString(),
              "<17>");
    we.expect("Probably.just(18).check(new IsModEq(3,2)) // 18 % 3 is not equal to 2",
              Probably.just(18).check(new IsModEq(3, 2)).toString(),
              "<>");
              
    we.expect("Probably.just(16).transform(new Incr())" +
              ".check(new IsModEq(3,2)) // 17 % 3 is not equal to 2",
              Probably.just(16).transform(new Incr()).check(new IsModEq(3, 2)).toString(),
              "<17>");
    we.expect("Probably.just(\"string\").transform(new Length())" +
              ".check(new IsModEq(3,2))",
              Probably.just("string").transform(new Length())
              .transform(new Incr()).transform(new Incr())
              .check(new IsModEq(3, 2)).toString(),
              "<8>");
    we.expect("Probably.<Integer>just(null).check(new IsModEq(0,2))",
              Probably.<Integer>just(null).check(new IsModEq(0, 2)).toString(),
              "<>");
  }
}