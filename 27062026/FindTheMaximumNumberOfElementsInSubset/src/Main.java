import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Solution {
    public int maximumLength(final int[] nums) {
        final Map<Integer, Integer> count = new HashMap<>();
        int maxNumber = 0;
        int answer = 1;

        for (final int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
            maxNumber = Math.max(maxNumber, num);
        }

        for (final int num : count.keySet()) {
            if (num == 1) {
                continue;
            }

            long current = num;
            int currentLength = 0;

            while (count.getOrDefault((int) current, 0) >= 2) {
                currentLength += 2;
                current = (current * current);

                if (current >= maxNumber) {
                    break;
                }
            }

            if (count.getOrDefault((int) current, 0) >= 1) {
                ++currentLength;
                answer = Math.max(answer, currentLength);
            } else {
                answer = Math.max(answer, currentLength - 1);
            }
        }

        final int onesCount = count.getOrDefault(1, 0);

        if ((onesCount & 1) > 0) {
            answer = Math.max(answer, onesCount);
        } else {
            answer = Math.max(answer, onesCount - 1);
        }

        return answer;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] nums = new int[n];

        for (int i = 0; i < n; ++i) {
            nums[i] = scanner.nextInt();
        }

        System.out.println(new Solution().maximumLength(nums));

        scanner.close();
    }
}
