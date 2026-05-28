import java.util.Scanner;

class Solution {
    private static class TrieNode {
        private final TrieNode[] children;
        private int index;
        private int length;

        TrieNode(final int index, final int length) {
            children = new TrieNode[26];
            this.index = index;
            this.length = length;
        }

        public void add(final String s, final int index) {
            TrieNode pCrawl = this;
            final int len = s.length();

            for (int i = len - 1; i >= 0; --i) {
                final char ch = s.charAt(i);
                final int idx = ch - 'a';

                if (pCrawl.children[idx] == null) {
                    pCrawl.children[idx] = new TrieNode(index, len);
                } else if (pCrawl.children[idx].length > len) {
                    pCrawl.children[idx].length = len;
                    pCrawl.children[idx].index = index;
                } else if (pCrawl.children[idx].length == len && pCrawl.children[idx].index > index) {
                    pCrawl.children[idx].index = index;
                }

                pCrawl = pCrawl.children[idx];
            }
        }

        public int searchIndex(final String s) {
            TrieNode pCrawl = this;
            final int len = s.length();

            for (int i = len - 1; i >= 0; --i) {
                final char ch = s.charAt(i);
                final int idx = ch - 'a';

                if (pCrawl.children[idx] == null) {
                    return pCrawl.index;
                }

                pCrawl = pCrawl.children[idx];
            }

            return pCrawl.index;
        }
    }

    public int[] stringIndices(final String[] wordsContainer, final String[] wordsQuery) {
        final int n = wordsContainer.length;
        final int q = wordsQuery.length;
        final int[] answer = new int[q];
        int minLenIndex = 0;

        for (int i = 1; i < n; ++i) {
            if (wordsContainer[i].length() < wordsContainer[minLenIndex].length()) {
                minLenIndex = i;
            }
        }

        final TrieNode root = new TrieNode(minLenIndex, 0);

        for (int i = 0; i < n; ++i) {
            root.add(wordsContainer[i], i);
        }

        for (int i = 0; i < q; ++i) {
            answer[i] = root.searchIndex(wordsQuery[i]);
        }

        return answer;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final String[] wordsContainer = new String[n];

        for (int i = 0; i < n; ++i) {
            wordsContainer[i] = scanner.next();
        }

        final int q = scanner.nextInt();
        final String[] wordsQuery = new String[q];

        for (int i = 0; i < q; ++i) {
            wordsQuery[i] = scanner.next();
        }

        final int[] answer = new Solution().stringIndices(wordsContainer, wordsQuery);

        for (final int x : answer) {
            System.out.print(x + " ");
        }
        System.out.println();

        scanner.close();
    }
}
