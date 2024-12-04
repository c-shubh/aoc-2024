import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Dec3 {
  static final boolean debug = !true;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    long mulSum = 0;
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      var pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
      var matcher = pattern.matcher(line);
      while (matcher.find()) {
        var parts = matcher.group().split(",");
        if (debug)
          System.out.println(Arrays.toString(parts));
        var a = Integer.parseInt(parts[0].substring(4));
        var b = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
        if (debug)
          System.out.println(a * b);
        mulSum += a * b;
      }
    }
    System.out.println(mulSum);

    sc.close();
  }
}
