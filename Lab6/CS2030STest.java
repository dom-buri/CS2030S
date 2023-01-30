import java.net.URI;
import java.util.List;
import java.util.ArrayList;
import javax.tools.DiagnosticCollector;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class CS2030STest {

  private static final String ANSI_RESET = "\u001B[0m";
  private static final String ANSI_RED = "\u001B[31m";
  private static final String ANSI_GREEN = "\u001B[32m";

  public void expect(String test, Object output, Object expect) {
    System.out.print(test);
    if ((expect == null && output == null) || output.equals(expect)) {
      System.out.println(".. " + ANSI_GREEN + "ok" + ANSI_RESET);
    } else {
      System.out.println(".. " + ANSI_RED + "failed" + ANSI_RESET);
      System.out.println("  expected: " + expect);
      System.out.println("  got this: " + output);
    }
  }
  
  public static String clean(String txt) {
    String res = "";
    for (int i=0; i<txt.length(); i++) {
      if (txt.charAt(i) != '\r' && txt.charAt(i) != '\n') {
        res += txt.charAt(i);
      }
    }
    return res;
  }
  public void expectPrint(String test, Object expect, ByteArrayOutputStream baos, PrintStream old) {
    System.out.flush();
    System.setOut(old);
    expect(test, CS2030STest.clean(baos.toString()), expect);
  }

  public void expectCompile(String test, String statement, boolean success) {
    System.out.print(test);

    class JavaSourceFromString extends SimpleJavaFileObject {
      final String code;

      JavaSourceFromString(String code) {
        super(URI.create("string:///TempClass.java"), Kind.SOURCE);
        this.code = "class TempClass {void foo(){" +  code + ";}}";
      }

      @Override
      public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
      }
    }

    boolean noError = ToolProvider
        .getSystemJavaCompiler()
        .getTask(null, null, new DiagnosticCollector<>(), null, null, 
            List.of(new JavaSourceFromString(statement)))
        .call();

    if (noError != success) {
      System.out.println(".. " + ANSI_RED + "failed" + ANSI_RESET);
      if (!success) {
        System.out.println("  expected compilation error but it compiles fine.");
      } else {
        System.out.println("  expected the statement to compile without errors but it does not.");
      }
    } else {
      System.out.println(".. " + ANSI_GREEN + "ok" + ANSI_RESET);
    }
  }
}
