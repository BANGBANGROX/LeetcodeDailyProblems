import java.util.Scanner;

class Solution {
    private int[] arr;
    private int d;
    private int[] maxVisits;
    private int n;

    public int maxJumps(final int[] arr, final int d) {
        this.arr = arr;
        this.d = d;
        n = arr.length;
        maxVisits = new int[n];
        int answer = 0;

        for (int i = 0; i < n; ++i) {
            answer = Math.max(answer, computeMaxVisits(i));
        }

        return answer;
    }

    private int computeMaxVisits(final int idx) {
        if (maxVisits[idx] != 0) {
            return maxVisits[idx];
        }

        int visits = 0;

        for (int i = idx - 1; i >= Math.max(0, idx - d); --i) {
            if (arr[i] < arr[idx]) {
                visits = Math.max(visits, computeMaxVisits(i));
            } else {
                break;
            }
        }

        for (int i = idx + 1; i <= Math.min(n - 1, idx + d); ++i) {
            if (arr[i] < arr[idx]) {
                visits = Math.max(visits, computeMaxVisits(i));
            } else {
                break;
            }
        }

        return maxVisits[idx] = visits + 1;
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

        final int d = scanner.nextInt();

        System.out.println(new Solution().maxJumps(arr, d));

        scanner.close();
    }
}
