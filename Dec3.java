import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Dec3 {
  static final boolean debug = !true;

  static class Part1 {

    public static void main() {
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

  static class Part2 {
    enum Type {
      DO, DONT, MUL
    }

    record Holder(int index, Type type, int mulA, int mulB) {
    }

    public static void main() {
      Scanner sc = new Scanner(System.in);
      long mulSum = 0;
      boolean evaluate = true;
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        var mulPattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
        var matcher = mulPattern.matcher(line);
        var list = new ArrayList<Holder>();
        while (matcher.find()) {
          var parts = matcher.group().split(",");
          var a = Integer.parseInt(parts[0].substring(4));
          var b = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
          list.add(new Holder(matcher.start(), Type.MUL, a, b));
        }
        int idx = 0;
        while (true) {
          idx = line.indexOf("do()", idx);
          if (idx >= 0) {
            list.add(new Holder(idx, Type.DO, 0, 0));
            idx++;
          } else
            break;
        }
        idx = 0;
        while (true) {
          idx = line.indexOf("don't()", idx);
          if (idx >= 0) {
            list.add(new Holder(idx, Type.DONT, 0, 0));
            idx++;
          } else
            break;
        }

        list.sort((a, b) -> Integer.compare(a.index, b.index));
        if (debug)
          System.out.println(list);

        for (var ele : list) {
          if (ele.type == Type.DO) {
            evaluate = true;
          } else if (ele.type == Type.DONT) {
            evaluate = false;
          } else if (ele.type == Type.MUL && evaluate) {
            mulSum += ele.mulA * ele.mulB;
          }
        }
      }
      System.out.println(mulSum);
      sc.close();
    }
  }

  public static void main(String[] args) {
    Part2.main();
  }
}
