import java.util.Scanner;

class Solution {
    private static class TrieNode {
        private final TrieNode[] children;

        TrieNode() {
            children = new TrieNode[10];
        }

        public void add(final int num) {
            TrieNode pCrawl = this;
            final String numString = String.valueOf(num);

            for (final char ch : numString.toCharArray()) {
                if (pCrawl.children[ch - '0'] == null) {
                    pCrawl.children[ch - '0'] = new TrieNode();
                }

                pCrawl = pCrawl.children[ch - '0'];
            }
        }

        public int getMaxLengthMatch(final int num) {
            TrieNode pCrawl = this;
            final String numString = String.valueOf(num);
            int answer = 0;

            for (final char ch : numString.toCharArray()) {
                if (pCrawl.children[ch - '0'] == null) {
                    return answer;
                }

                pCrawl = pCrawl.children[ch - '0'];
                ++answer;
            }

            return answer;
        }
    }

    public int longestCommonPrefix(final int[] arr1, final int[] arr2) {
        final TrieNode arr1TrieNode = new TrieNode();
        final TrieNode arr2TrieNode = new TrieNode();
        int answer = 0;

        for (final int num : arr1) {
            arr1TrieNode.add(num);
        }

        for (final int num : arr2) {
            arr2TrieNode.add(num);
        }

        for (final int num : arr1) {
            answer = Math.max(answer, arr2TrieNode.getMaxLengthMatch(num));
        }

        for (final int num : arr2) {
            answer = Math.max(answer, arr1TrieNode.getMaxLengthMatch(num));
        }

        return answer;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int m = scanner.nextInt();
        final int[] arr1 = new int[m];

        for (int i = 0; i < m; ++i) {
            arr1[i] = scanner.nextInt();
        }

        final int n = scanner.nextInt();
        final int[] arr2 = new int[n];

        for (int i = 0; i < n; ++i) {
            arr2[i] = scanner.nextInt();
        }

        System.out.println(new Solution().longestCommonPrefix(arr1, arr2));

        scanner.close();
    }
}
