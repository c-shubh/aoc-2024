import java.util.Arrays;
import java.util.Scanner;

public class Dec7 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    long ans = 0;
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      String[] split = line.split(":");
      long testValue = Long.parseLong(split[0]);
      long[] nums = Arrays
          .stream(split[1].strip().split(" "))
          .mapToLong(Long::parseLong)
          .toArray();
      if (canProduceTestValue(testValue, nums, 0, 0)) {
        ans += testValue;
      }
    }
    System.out.println(ans);
  }

  static boolean canProduceTestValue(
      long testValue,
      long[] nums,
      int i,
      long sum) {
    if (i == nums.length) {
      return sum == testValue;
    }
    return canProduceTestValue(testValue, nums, i + 1, sum + nums[i])
        || canProduceTestValue(testValue, nums, i + 1, sum * nums[i]);
  }
}