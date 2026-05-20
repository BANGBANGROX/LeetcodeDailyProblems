import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Solution {
    public int[] findThePrefixCommonArray(final int[] A, final int[] B) {
        final int n = A.length;
        final int[] answer = new int[n];
        final Set<Integer> visited = new HashSet<>();

        answer[0] = (A[0] == B[0] ? 1 : 0);

        visited.add(A[0]);
        visited.add(B[0]);

        for (int i = 1; i < n; ++i) {
            answer[i] = answer[i - 1];

            if (visited.contains(A[i]) || A[i] == B[i]) {
                ++answer[i];
            }

            visited.add(A[i]);

            if (visited.contains(B[i]) && A[i] != B[i]) {
                ++answer[i];
            }

            visited.add(B[i]);
        }

        return answer;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] A = new int[n];
        final int[] B = new int[n];

        for (int i = 0; i < n; ++i) {
            A[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; ++i) {
            B[i] = scanner.nextInt();
        }

        final int[] answer = new Solution().findThePrefixCommonArray(A, B);

        for (final int x : answer) {
            System.out.print(x + " ");
        }
        System.out.println();

        scanner.close();
    }
}
