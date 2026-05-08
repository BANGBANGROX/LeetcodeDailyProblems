import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Solution {
    private static final int MAX_NUM = 1_000_005;
    private static final Map<Integer, List<Integer>> NUMBER_TO_PRIME_FACTORS_MAP = new HashMap<>();

    static {
        for (int i = 1; i < MAX_NUM; ++i) {
            NUMBER_TO_PRIME_FACTORS_MAP.put(i, new ArrayList<>());
        }

        for (int i = 2; i < MAX_NUM; ++i) {
            if (NUMBER_TO_PRIME_FACTORS_MAP.get(i).isEmpty()) {
                for (int j = i; j < MAX_NUM; j += i) {
                    NUMBER_TO_PRIME_FACTORS_MAP.get(j).add(i);
                }
            }
        }
    }

    public int minJumps(final int[] nums) {
        final int n = nums.length;
        final Map<Integer, List<Integer>> primeNumberToIndicesMap = new HashMap<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        final boolean[] visited = new boolean[n];
        int answer = 0;

        for (int i = 0; i < n; ++i) {
            if (NUMBER_TO_PRIME_FACTORS_MAP.get(nums[i]).size() == 1) {
                primeNumberToIndicesMap.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
            }
        }

        queue.add(n - 1);
        visited[n - 1] = true;

        while (true) {
            final ArrayDeque<Integer> nextQueue = new ArrayDeque<>();

            while (!queue.isEmpty()) {
                final int idx = queue.pollFirst();

                if (idx == 0) {
                    return answer;
                }

                if (idx - 1 >= 0 && !visited[idx - 1]) {
                    nextQueue.add(idx - 1);
                    visited[idx - 1] = true;
                }

                if (idx + 1 < n && !visited[idx + 1]) {
                    nextQueue.add(idx + 1);
                    visited[idx + 1] = true;
                }

                for (final int primeNumber : NUMBER_TO_PRIME_FACTORS_MAP.get(nums[idx])) {
                    if (primeNumberToIndicesMap.containsKey(primeNumber)) {
                        for (final int nextIdx : primeNumberToIndicesMap.get(primeNumber)) {
                            if (!visited[nextIdx]) {
                                nextQueue.add(nextIdx);
                                visited[nextIdx] = true;
                            }
                        }
                        primeNumberToIndicesMap.remove(primeNumber);
                    }
                }
            }

            ++answer;
            queue = nextQueue;
        }
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

        System.out.println(new Solution().minJumps(nums));

        scanner.close();
    }
}
