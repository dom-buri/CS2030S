import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class Test1 {
  public static void main(String[] args) { 
    ByteArrayOutputStream baos;
    PrintStream ps;
    
    baos = new ByteArrayOutputStream();
    ps = new PrintStream(baos);
    PrintStream old = System.out;
    System.setOut(ps);

    CS2030STest we = new CS2030STest();

    new Print().call(17);
    we.expectPrint("new Print().call(17)",
                   "17",
                   baos,
                   old);
    
    baos = new ByteArrayOutputStream();
    ps = new PrintStream(baos);
    System.setOut(ps);
    
    new Print().call("string");
    we.expectPrint("new Print().call(\"string\")",
                   "string",
                   baos,
                   old);
  }
}