class Test6 {
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
    
    Probably<Immutator<Integer, Integer>> justIncr = Probably.just(new Incr());
    Probably<Immutator<Integer, String>> justLength = Probably.just(new Length());
    Probably<Immutator<Integer, Integer>> noIncr = Probably.none();
    Probably<Immutator<Integer, String>> noLength = Probably.none();
    
    we.expect("Probably.just(17).<Integer>apply(justIncr)",
              Probably.just(17).apply(justIncr).toString(),
              "<18>");
    we.expect("Probably.<Integer>none().<Integer>apply(justIncr)",
              Probably.<Integer>none().apply(justIncr).toString(),
              "<>");
    we.expect("Probably.just(17).<Integer>apply(noIncr)",
              Probably.just(17).apply(noIncr).toString(),
              "<>");
    we.expect("Probably.<Integer>none().<Integer>apply(noIncr)",
              Probably.<Integer>none().apply(noIncr).toString(),
              "<>");
    
    we.expect("Probably.just(\"string\").<Integer>apply(justLength)",
              Probably.just("string").apply(justLength).toString(),
              "<6>");
    we.expect("Probably.<String>none().<Integer>apply(justLength)",
              Probably.<String>none().apply(justLength).toString(),
              "<>");
    we.expect("Probably.just(\"string\").<Integer>apply(noLength)",
              Probably.just("string").apply(noLength).toString(),
              "<>");
    we.expect("Probably.<String>none().<Integer>apply(noLength)",
              Probably.<String>none().apply(noLength).toString(),
              "<>");
  }
}