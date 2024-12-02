import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Dec1 {

  static void part1() {
    Scanner sc = new Scanner(System.in);
    var leftList = new ArrayList<Integer>();
    var rightList = new ArrayList<Integer>();
    while (sc.hasNext()) {
      int n = sc.nextInt();
      leftList.add(n);
      n = sc.nextInt();
      rightList.add(n);
    }
    Collections.sort(leftList);
    Collections.sort(rightList);
    long totalDist = 0;
    for (int i = 0; i < leftList.size(); i++) {
      totalDist += Math.abs(leftList.get(i) - rightList.get(i));
    }
    System.out.println(totalDist);
    sc.close();
  }

  static void part2() {
    Scanner sc = new Scanner(System.in);
    var leftList = new ArrayList<Integer>();
    var rightFreq = new HashMap<Integer, Integer>();
    while (sc.hasNext()) {
      int n = sc.nextInt();
      leftList.add(n);
      n = sc.nextInt();
      rightFreq.put(n, rightFreq.getOrDefault(n, 0) + 1);
    }
    int similarityScore = 0;
    for (var ele : leftList) {
      similarityScore += ele * rightFreq.getOrDefault(ele, 0);
    }
    System.out.println(similarityScore);
    sc.close();
  }

  public static void main(String[] args) {
    part2();
  }
}