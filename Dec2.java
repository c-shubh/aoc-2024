import java.util.Arrays;
import java.util.Scanner;

public class Dec2 {

  static final boolean debug = false;

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

  static class Part2 {

    static int longestIncreasingSubsequence(int[] a) {
      int ans = Integer.MIN_VALUE;
      // stores length of LIS ending at i
      int[] dp = new int[a.length];
      for (int i = 0; i < a.length; i++) {
        dp[i] = 1; // itsef is a subseq
        // check all prev LIS
        for (int j = i - 1; j >= 0; j--) {
          int diff = a[i] - a[j];
          if (1 <= diff && diff <= 3) {
            dp[i] = Math.max(dp[i], 1 + dp[j]);
          }
        }
        ans = Math.max(ans, dp[i]);
      }
      return ans;
    }

    static int longestDecreasingSubsequence(int[] a) {
      int ans = Integer.MIN_VALUE;
      // stores length of LDS ending at i
      int[] dp = new int[a.length];
      for (int i = 0; i < a.length; i++) {
        dp[i] = 1; // itself is a subseq
        // check all prev LDS
        for (int j = i - 1; j >= 0; j--) {
          int diff = a[j] - a[i]; // reversed subtraction for decreasing
          if (1 <= diff && diff <= 3) {
            dp[i] = Math.max(dp[i], 1 + dp[j]);
          }
        }
        ans = Math.max(ans, dp[i]);
      }
      return ans;
    }

    static boolean isSafe(int[] nums) {
      return longestIncreasingSubsequence(nums) >= nums.length - 1
          || longestDecreasingSubsequence(nums) >= nums.length - 1;
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
    Part2.main();
  }
}