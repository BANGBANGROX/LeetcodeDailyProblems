import java.util.Scanner;

class Solution {
    public int getCommon(final int[] nums1, final int[] nums2) {
        final int m = nums1.length;
        final int n = nums2.length;
        int i = 0;
        int j = 0;

        while (i < m && j < n) {
            if (nums1[i] == nums2[j]) {
                return nums1[i];
            }

            if (nums1[i] < nums2[j]) {
                ++i;
            } else {
                ++j;
            }
        }

        return -1;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int m = scanner.nextInt();
        final int n = scanner.nextInt();
        final int[] nums1 = new int[m];
        final int[] nums2 = new int[n];

        for (int i = 0; i < m; ++i) {
            nums1[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; ++i) {
            nums2[i] = scanner.nextInt();
        }

        System.out.println(new Solution().getCommon(nums1, nums2));

        scanner.close();
    }
}
