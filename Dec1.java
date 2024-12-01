import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Dec1 {

  public static void main(String[] args) {
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
}