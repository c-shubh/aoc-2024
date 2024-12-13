import java.util.ArrayList;
import java.util.Scanner;

public class Dec9 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String line = sc.nextLine();
    var disk = new ArrayList<Integer>();
    int id = 0;
    for (int i = 0; i < line.length(); i++) {
      char c = line.charAt(i);
      if (i % 2 == 0) {
        for (int j = 0; j < c - '0'; j++) {
          disk.add(id);
        }
        id++;
      } else {
        for (int j = 0; j < c - '0'; j++) {
          disk.add(null);
        }
      }
    }

    int left = 0;
    int right = disk.size() - 1;
    while (left < right) {
      if (disk.get(left) != null)
        left++;
      else {
        // swap
        disk.set(left, disk.get(right));
        disk.set(right, null);
        right--;
      }
    }
    long checksum = 0;
    for (int i = 0; i < disk.size(); i++) {
      if (disk.get(i) == null)
        break;
      checksum += i * disk.get(i);
    }
    System.out.println(checksum);
  }
}