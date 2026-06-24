import java.util.Arrays;
import java.util.Scanner;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int states = 2 * m;

        // T[i][j] represents the number of ways to move from state j to state i
        long[][] T = new long[states][states];

        // First m states (0 to m-1): UP states (Current value x, next must be DOWN)
        // Next m states (m to 2m-1): DOWN states (Current value x, next must be UP)
        for (int x = 0; x < m; x++) {
            int upState = x;
            int downState = x + m;

            // From UP(x) -> can transition to DOWN(y) where y > x
            for (int y = x + 1; y < m; y++) {
                T[y + m][upState] = 1;
            }

            // From DOWN(x) -> can transition to UP(y) where y < x
            for (int y = 0; y < x; y++) {
                T[y][downState] = 1;
            }
        }

        // Base case: For length 1, every number can start as either an UP or DOWN state
        long[] start = new long[states];
        Arrays.fill(start, 1);

        // Raise transition matrix to the power of (n - 1)
        long[][] P = power(T, n - 1);

        // Multiply the resulting transformation matrix by the starting state vector
        long[] finalVec = multiply(P, start);

        long ans = 0;
        for (long val : finalVec) {
            ans = (ans + val) % MOD;
        }

        return (int) ans;
    }

    // Standard Matrix Exponentiation - O(States^3 * log N)
    private long[][] power(long[][] base, long exp) {
        int size = base.length;
        long[][] res = new long[size][size];
        for (int i = 0; i < size; i++) {
            res[i][i] = 1; // Identity Matrix
        }

        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiply(res, base);
            }
            base = multiply(base, base);
            exp >>= 1;
        }
        return res;
    }

    private long[][] multiply(long[][] A, long[][] B) {
        int size = A.length;
        long[][] C = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (A[i][k] == 0) continue; // Sparse optimization
                for (int j = 0; j < size; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    private long[] multiply(long[][] A, long[] v) {
        int size = A.length;
        long[] res = new long[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                res[i] = (res[i] + A[i][j] * v[j]) % MOD;
            }
        }
        return res;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int l = scanner.nextInt();
        final int r = scanner.nextInt();

        System.out.println(new Solution().zigZagArrays(n, l, r));

        scanner.close();
    }
}
