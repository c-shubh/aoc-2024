import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Dec6 {
  static final boolean debug = false;

  static void debug(Object... o) {
    if (debug) {
      for (Object x : o) {
        System.out.print(x + " ");
      }
      System.out.println();
    }
  }

  static char turnRight(char direction) {
    switch (direction) {
      case '^':
        return '>';
      case 'v':
        return '<';
      case '<':
        return '^';
      case '>':
        return 'v';
      default:
        return direction;
    }
  }

  static int getNextI(int i, char direction) {
    if (direction == '^')
      return i - 1;
    if (direction == 'v')
      return i + 1;
    return i;
  }

  static int getNextJ(int j, char direction) {
    if (direction == '<')
      return j - 1;
    if (direction == '>')
      return j + 1;
    return j;
  }

  static int simulate(
      char[][] grid,
      int starti,
      int startj) {
    var visited = new HashSet<List<Integer>>();
    int i = starti;
    int j = startj;
    debug("Initial i, j", i, j);
    char direction = grid[i][j];
    debug("Initial direction", direction);
    visited.add(List.of(i, j));
    while (true) {
      int nexti = getNextI(i, direction);
      int nextj = getNextJ(j, direction);

      debug("nexti, nextj", nexti, nextj);

      if (nexti >= 0 && nexti < grid.length && nextj >= 0 && nextj < grid[0].length) {
        debug("next i,j are in bounds");
        if (grid[nexti][nextj] == '#') {
          debug("Turning right");
          direction = turnRight(direction);
        } else {
          debug("continuing");
          i = nexti;
          j = nextj;
          visited.add(List.of(i, j));
          debug("visited: ", List.of(i, j));
        }
      } else {
        break;
      }
    }
    return visited.size();
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    char[][] grid = null;
    int arri = 0;
    int starti = 0, startj = 0;
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      char[] chars = new char[line.length()];
      for (int i = 0; i < line.length(); i++) {
        chars[i] = line.charAt(i);
        if ("^v<>".contains(String.valueOf(chars[i]))) {
          starti = arri;
          startj = i;
        }
      }
      if (grid == null) {
        grid = new char[chars.length][];
      }
      grid[arri++] = chars;

    }
    System.out.println(simulate(grid, starti, startj));
    sc.close();
  }
}
