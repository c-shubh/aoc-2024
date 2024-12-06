import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Dec5 {
  static final boolean debug = !true;

  public static void main(String[] args) {
    Part2.main(args);
  }

  static class Part1 {
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      // construct graph of page ordering rules
      var graph = new HashMap<Integer, Set<Integer>>();
      while (sc.hasNextLine()) {
        String line = sc.nextLine().trim();
        if (line.equals("")) {
          break;
        }
        if (debug)
          System.out.println("line: " + line);
        String[] split = line.split("\\|");
        if (debug) {
          System.out.println("split: " + Arrays.toString(split));
        }
        int a = Integer.parseInt(split[0]);
        int b = Integer.parseInt(split[1]);
        graph.putIfAbsent(a, new HashSet<>());
        graph.get(a).add(b);
      }
      if (debug)
        System.out.println("graph: " + graph);
      long middleSum = 0;
      // process page updates
      while (sc.hasNextLine()) {
        int[] pageNos = Arrays
            .stream(sc.nextLine().split(","))
            .mapToInt(Integer::parseInt)
            .toArray();
        if (debug)
          System.out.println("pageNos: " + Arrays.toString(pageNos));
        // add missing page nos to graphs
        for (var ele : pageNos) {
          graph.putIfAbsent(ele, new HashSet<Integer>());
        }
        if (isCorrectlyOrdered(graph, pageNos)) {
          if (debug)
            System.out.printf("processed: %s\n", Arrays.toString(pageNos));
          middleSum += pageNos[pageNos.length / 2];
        }
      }
      System.out.println(middleSum);
    }

    static boolean isCorrectlyOrdered(
        HashMap<Integer, Set<Integer>> graph,
        int[] pageNos) {
      for (int i = 0; i < pageNos.length - 1; i++) {
        // can we go to the next page no?
        // if it's a neighbor of current page no, then yes
        if (!graph.get(pageNos[i]).contains(pageNos[i + 1])) {
          return false;
        }
      }
      return true;
    }
  }

  static class Part2 {
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      // construct graph of page ordering rules
      var graph = new HashMap<Integer, Set<Integer>>();
      while (sc.hasNextLine()) {
        String line = sc.nextLine().trim();
        if (line.equals("")) {
          break;
        }
        if (debug)
          System.out.println("line: " + line);
        String[] split = line.split("\\|");
        if (debug) {
          System.out.println("split: " + Arrays.toString(split));
        }
        int a = Integer.parseInt(split[0]);
        int b = Integer.parseInt(split[1]);
        graph.putIfAbsent(a, new HashSet<>());
        graph.get(a).add(b);

      }
      if (debug)
        System.out.println("graph: " + graph);
      long middleSum = 0;
      // process page updates
      while (sc.hasNextLine()) {
        int[] pageNos = Arrays
            .stream(sc.nextLine().split(","))
            .mapToInt(Integer::parseInt)
            .toArray();
        if (debug)
          System.out.println("pageNos: " + Arrays.toString(pageNos));
        // add missing page nos to graphs
        for (var ele : pageNos) {
          graph.putIfAbsent(ele, new HashSet<Integer>());
        }
        if (!isCorrectlyOrdered(graph, pageNos)) {
          orderCorrectly(graph, pageNos);
          middleSum += pageNos[pageNos.length / 2];
        }
      }
      System.out.println(middleSum);
    }

    static void orderCorrectly(
        HashMap<Integer, Set<Integer>> graph,
        int[] pageNos) {
      var pageNoSet = Arrays.stream(pageNos).boxed().collect(Collectors.toSet());

      var indegrees = new HashMap<Integer, Integer>();
      pageNoSet.forEach(ele -> indegrees.put(ele, 0));

      for (Integer ele : pageNoSet) {
        for (Integer neigh : graph.get(ele)) {
          if (pageNoSet.contains(neigh)) {
            indegrees.put(neigh, indegrees.get(neigh) + 1);
          }
        }
      }

      if (debug)
        System.out.println("indegrees: " + indegrees);
      // kahn's algo
      int pageNosI = pageNos.length - 1;
      while (pageNosI >= 0) {
        for (var kv : indegrees.entrySet()) {
          if (kv.getValue().equals(0)) {
            pageNos[pageNosI--] = kv.getKey();
            // decrement indegree of neighbors who are part of pageNoSet
            for (var neigh : graph.get(kv.getKey()))
              if (pageNoSet.contains(neigh))
                indegrees.put(neigh, indegrees.get(neigh) - 1);
            indegrees.put(kv.getKey(), -1);
          }
        }
      }
    }

    static boolean isCorrectlyOrdered(
        HashMap<Integer, Set<Integer>> graph,
        int[] pageNos) {
      for (int i = 0; i < pageNos.length - 1; i++) {
        // can we go to the next page no?
        // if it's a neighbor of current page no, then yes
        if (!graph.get(pageNos[i]).contains(pageNos[i + 1])) {
          return false;
        }
      }
      return true;
    }
  }
}