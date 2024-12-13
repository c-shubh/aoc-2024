import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Dec8 {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int gridSize = Integer.MIN_VALUE;
    var antennas = new HashMap<Character, List<List<Integer>>>();
    for (int i = 0; sc.hasNextLine(); i++) {
      String line = sc.nextLine();
      gridSize = Math.max(gridSize, line.length());
      for (int j = 0; j < line.length(); j++) {
        if (line.charAt(j) != '.') {
          antennas.putIfAbsent(line.charAt(j), new ArrayList<>());
          antennas.get(line.charAt(j)).add(List.of(i, j));
        }
      }
    }
    var antinodes = new HashSet<List<Integer>>();
    for (var kv : antennas.entrySet()) {
      var positions = kv.getValue();
      for (int i = 0; i < positions.size(); i++) {
        for (int j = i + 1; j < positions.size(); j++) {
          var potentialAntinodes = getAntinodes(positions.get(i), positions.get(j));
          for (var antinode : potentialAntinodes) {
            int ai = antinode.get(0);
            int aj = antinode.get(1);
            if (ai >= 0 && ai < gridSize && aj >= 0 && aj < gridSize) {
              antinodes.add(antinode);
            }
          }
        }
      }
    }
    System.out.println(antinodes.size());
  }

  static List<List<Integer>> getAntinodes(List<Integer> a, List<Integer> b) {
    int ai = a.get(0);
    int aj = a.get(1);
    int bi = b.get(0);
    int bj = b.get(1);
    int down = bi - ai;
    int horiz = bj - aj;
    return List.of(
        List.of(ai - down, aj - horiz),
        List.of(bi + down, bj + horiz));
  }
}