import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(final int val) {
        this.val = val;
    }
}

class Solution {
    public TreeNode createBinaryTree(final int[][] descriptions) {
        final Map<Integer, TreeNode> valToTreeNodeMap = new HashMap<>();
        final Set<Integer> potentialRootSet = new HashSet<>();
        final Set<Integer> childNodesSet = new HashSet<>();

        for (final int[] description : descriptions) {
            final int child = description[1];
            final int parent = description[0];
            final boolean isLeft = (description[2] == 1);
            final TreeNode parentNode = valToTreeNodeMap.computeIfAbsent(parent, k -> new TreeNode(parent));
            final TreeNode childNode = valToTreeNodeMap.computeIfAbsent(child, k -> new TreeNode(child));

            if (isLeft) {
                parentNode.left = childNode;
            } else {
                parentNode.right = childNode;
            }

            potentialRootSet.remove(child);
            childNodesSet.add(child);

            if (!childNodesSet.contains(parent)) {
                potentialRootSet.add(parent);
            }
        }

        return valToTreeNodeMap.get(potentialRootSet.iterator().next());
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[][] descriptions = new int[n][3];

        for (int i = 0; i < n; ++i) {
            descriptions[i][0] = scanner.nextInt();
            descriptions[i][1] = scanner.nextInt();
            descriptions[i][2] = scanner.nextInt();
        }

        final TreeNode root = new Solution().createBinaryTree(descriptions);
        traverse(root);

        System.out.println();

        scanner.close();
    }

    private static void traverse(final TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.val + " ");

        traverse(root.left);
        traverse(root.right);
    }
}
