class Test3 {
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
    
    we.expect("new Incr().invoke(4)",
              new Incr().invoke(4).toString(),
              "5");
    we.expect("new Incr().invoke(new Incr().invoke(4))",
              new Incr().invoke(new Incr().invoke(4)).toString(),
              "6");
    we.expect("new Length().invoke(\"string\")",
              new Length().invoke("string").toString(),
              "6");
    we.expect("new Incr().invoke(new Length().invoke(\"string\"))",
              new Incr().invoke(new Length().invoke("string")).toString(),
              "7");

    we.expect("new Improbable<>().invoke(1)",
              new Improbable<Integer>().invoke(1).toString(),
              "<1>");
    we.expect("new Improbable<String>().invoke(null)",
              new Improbable<String>().invoke(null).toString(),
              "<>");
    we.expect("new Improbable<Integer>().invoke(1).transform(new Incr())",
              new Improbable<Integer>().invoke(1).transform(new Incr()).toString(),
              "<2>");
    we.expect("new Improbable<>().invoke(new Improbable<>().invoke(1))",
              new Improbable<>().invoke(new Improbable<Integer>().invoke(1)).toString(),
              "<<1>>");
  }
}
