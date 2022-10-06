class TestProbably {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    
    we.expect("Probably.just(4)",
              Probably.just(4).toString(),
              "<4>");
    we.expect("Probably.just(Probably.just(0))",
              Probably.just(Probably.just(0)).toString(),
              "<<0>>");
    we.expect("Probably.just(Probably.just(Probably.just(\"null\")))",
              Probably.just(Probably.just(Probably.just("null"))).toString(),
              "<<<null>>>");
    we.expect("Probably.just(Probably.just(Probably.none()))",
              Probably.just(Probably.just(Probably.none())).toString(),
              "<<<>>>");
    we.expect("Probably.just(Probably.just(null))",
              Probably.just(Probably.just(null)).toString(),
              "<<>>");
              
    we.expect("Probably.just(4).equals(Probably.just(4))",
              Probably.just(4).equals(Probably.just(4)),
              true);
    we.expect("Probably.just(4).equals(4)",
              Probably.just(4).equals(4),
              false);
    we.expect("Probably.just(Probably.just(0)).equals(Probably.just(0))",
              Probably.just(Probably.just(0)).equals(Probably.just(0)),
              false);
    we.expect("Probably.just(Probably.just(0)).equals(Probably.just(Probably.just(0)))",
              Probably.just(Probably.just(0)).equals(Probably.just(Probably.just(0))),
              true);
              
    we.expect("Probably.just(\"string\")",
              Probably.just("string").toString(),
              "<string>");
              
    we.expect("Probably.just(\"string\").equals(Probably.just(4))",
              Probably.just("string").equals(Probably.just(4)),
              false);
    we.expect("Probably.just(\"string\").equals(Probably.just(\"null\"))",
              Probably.just("string").equals(Probably.just("null")),
              false);
              
    we.expect("Probably.just(null)",
              Probably.just(null).toString(),
              "<>");
    we.expect("Probably.none()",
              Probably.none().toString(),
              "<>");
    we.expect("Probably.none().equals(Probably.just(null))",
              Probably.none().equals(Probably.just(null)),
              true);
    we.expect("Probably.none() == Probably.just(null)",
              Probably.none() == Probably.just(null),
              true);
  }
}