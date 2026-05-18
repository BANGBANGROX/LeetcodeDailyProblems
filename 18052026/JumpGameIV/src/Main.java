import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

class Solution {
    public int minJumps(final int[] arr) {
        final int n = arr.length;

        if (n == 1) {
            return 0;
        }

        final boolean[] visited = new boolean[n];
        final Queue<Integer> queue = new ArrayDeque<>();
        final Map<Integer, List<Integer>> numIndices = new HashMap<>();
        int answer = 0;

        for (int i = 0; i < n; ++i) {
            numIndices.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        queue.offer(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            final int size = queue.size();

            for (int i = 0; i < size; ++i) {
                final int node = queue.poll();

                if (numIndices.containsKey(arr[node])) {
                    for (final int idx : numIndices.get(arr[node])) {
                        if (idx == n - 1) {
                            return answer + 1;
                        }

                        queue.offer(idx);
                        visited[idx] = true;
                    }

                    numIndices.remove(arr[node]);
                }

                if (node - 1 >= 0 && !visited[node - 1]) {
                    queue.offer(node - 1);
                    visited[node - 1] = true;
                }

                if (node + 1 < n && !visited[node + 1]) {
                    if (node + 1 == n - 1) {
                        return answer + 1;
                    }

                    queue.offer(node + 1);
                    visited[node + 1] = true;
                }
            }

            ++answer;
        }

        return -1;
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

        System.out.println(new Solution().minJumps(arr));

        scanner.close();
    }
}
