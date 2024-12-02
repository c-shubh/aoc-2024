import java.util.Arrays;
import java.util.Scanner;

public class Dec2 {

  static final boolean debug = true;

  static class Part1 {
    static boolean isSafe(int[] nums) {
      boolean isIncreasing = nums[1] > nums[0];
      for (int i = 1; i < nums.length; i++) {
        int diff = Math.abs(nums[i] - nums[i - 1]);
        if (isIncreasing != (nums[i] > nums[i - 1]))
          return false;
        if (!(diff >= 1 && diff <= 3))
          return false;
      }
      return true;
    }

    public static void main() {
      Scanner sc = new Scanner(System.in);
      int safeCount = 0;
      while (sc.hasNextLine()) {
        int[] nums = Arrays.stream(
            sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        if (isSafe(nums)) {
          safeCount++;
          if (debug)
            System.out.println("Safe: " + Arrays.toString(nums));
        } else {
          if (debug)
            System.out.println("Unsafe: " + Arrays.toString(nums));
        }
      }
      System.out.println(safeCount);
      sc.close();
    }
  }

  public static void main(String[] args) {
    Part1.main();
  }
}