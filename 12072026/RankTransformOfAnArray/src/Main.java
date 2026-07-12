import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Solution {
    public int[] arrayRankTransform(final int[] arr) {
        final int n = arr.length;
        final int[] sortedArr = arr.clone();
        final int[] answer = new int[n];
        final Map<Integer, Integer> numToRank = new HashMap<>();
        int rank = 1;

        Arrays.sort(sortedArr);

        for (int i = 0; i < n; ++i) {
            numToRank.put(sortedArr[i], rank);

            if (i + 1 < n && sortedArr[i] != sortedArr[i + 1]) {
                ++rank;
            }
        }

        for (int i = 0; i < n; ++i) {
            answer[i] = numToRank.get(arr[i]);
        }

        return answer;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] arr = new int[n];

        for (int i = 0; i < n; ++i) {
            arr[i] = scanner.nextInt();
        }

        final int[] answer = new Solution().arrayRankTransform(arr);

        for (final int x : answer) {
            System.out.print(x + " ");
        }
        System.out.println();

        scanner.close();
    }
}
