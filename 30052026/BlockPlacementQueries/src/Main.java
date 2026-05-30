import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

class RangeMaxSegmentTree {
    private final int[] tree;
    private final int n;

    public RangeMaxSegmentTree(final int n) {
        tree = new int[4 * n];
        this.n = n;
    }

    public void update(final int index, final int value) {
        update(1, 0, n - 1, index, value);
    }

    public int query(final int start, final int end) {
        return query(1, 0, n - 1, start, end);
    }

    private void update(final int node, final int left, final int right, final int index, final int value) {
        if (left == right) {
            tree[node] = value;
            return;
        }

        final int mid = (left + ((right - left) >> 1));

        if (index <= mid) {
            update(2 * node, left, mid, index, value);
        } else {
            update(2 * node + 1, mid + 1, right, index, value);
        }

        tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
    }

    private int query(final int node, final int left, final int right, final int start, final int end) {
        // No overlap
        if (end < left || start > right) {
            return 0;
        }

        // Full overlap
        if (start <= left && end >= right) {
            return tree[node];
        }

        final int mid = (left + ((right - left) >> 1));

        return Math.max(
                query(2 * node, left, mid, start, end),
                query(2 * node + 1, mid + 1, right, start, end)
        );
    }
}

class Solution {
    public List<Boolean> getResults(final int[][] queries) {
        final int n = Math.min(5 * 10000, 3 * queries.length) + 1;
        final RangeMaxSegmentTree rangeMaxSegmentTree = new RangeMaxSegmentTree(n);
        final TreeSet<Integer> obstacles = new TreeSet<>();
        final List<Boolean> answer = new ArrayList<>();

        obstacles.add(0);
        obstacles.add(n);

        rangeMaxSegmentTree.update(0, n);

        for (final int[] query : queries) {
            if (query[0] == 1) {
                final int x = query[1];
                final int leftObstacle = obstacles.lower(x);
                final int rightObstacle = obstacles.higher(x);

                rangeMaxSegmentTree.update(leftObstacle, x - leftObstacle);
                rangeMaxSegmentTree.update(x, rightObstacle - x);
                obstacles.add(x);
            } else {
                final int x = query[1];
                final int sz = query[2];
                final int gap = rangeMaxSegmentTree.query(0, x - sz);

                answer.add(gap >= sz);
            }
        }

        return answer;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int q = scanner.nextInt();
        final int[][] queries = new int[q][];
        
        for (int i = 0; i < q; ++i) {
            final int type = scanner.nextInt();
            
            if (type == 1) {
                queries[i] = new int[2];
                queries[i][1] = scanner.nextInt();
            } else {
                queries[i] = new int[3];
                queries[i][1] = scanner.nextInt();
                queries[i][2] = scanner.nextInt();
            }

            queries[i][0] = type;
        }

        System.out.println(new Solution().getResults(queries));

        scanner.close();
    }
}
