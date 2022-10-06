class Test4 {
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
    
    we.expect("Probably.just(4).transform(new Incr())",
              Probably.just(4).transform(new Incr()).toString(),
              "<5>");
    we.expect("Probably.just(4).transform(new Incr()).transform(new Incr())",
              Probably.just(4).transform(new Incr()).transform(new Incr()).toString(),
              "<6>");
    we.expect("Probably.just(\"string\").transform(new Length())",
              Probably.just("string").transform(new Length()).toString(),
              "<6>");
    we.expect("Probably.just(\"string\").transform(new Length()).transform(new Incr())",
              Probably.just("string").transform(new Length()).transform(new Incr()).toString(),
              "<7>");
              
    we.expect("Probably.<Integer>none().transform(new Incr())",
              Probably.<Integer>none().transform(new Incr()).toString(),
              "<>");
    we.expect("Probably.<String>none().transform(new Length())",
              Probably.<String>none().transform(new Length()).toString(),
              "<>");
    we.expect("Probably.<String>just(null).transform(new Length()).transform(new Incr())",
              Probably.<String>just(null).transform(new Length()).transform(new Incr()).toString(),
              "<>");
  }
}