import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class Solution {
    public boolean canReach(final int[] arr, final int start) {
        final int n = arr.length;
        final boolean[] visited = new boolean[n];
        final Queue<Integer> queue = new ArrayDeque<>();

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            final int index = queue.poll();

            if (arr[index] == 0) {
                return true;
            }

            if (index - arr[index] >= 0 && !visited[index - arr[index]]) {
                queue.offer(index - arr[index]);
                visited[index - arr[index]] = true;
            }

            if (index + arr[index] < n && !visited[index + arr[index]]) {
                queue.offer(index + arr[index]);
                visited[index + arr[index]] = true;
            }
        }

        return false;
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

        final int start = scanner.nextInt();

        System.out.println(new Solution().canReach(arr, start));

        scanner.close();
    }
}
