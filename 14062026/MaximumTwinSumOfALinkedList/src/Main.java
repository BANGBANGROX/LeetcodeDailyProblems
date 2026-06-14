import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ListNode {
    int val;
    ListNode next;

    public ListNode(final int val) {
        this.val = val;
        next = null;
    }
}

class Solution {
    public int pairSum(final ListNode head) {
        final List<Integer> nums = new ArrayList<>();
        int answer = 0;

        for (ListNode current = head; current != null; current = current.next) {
            nums.add(current.val);
        }

        int left = 0;
        int right = nums.size() - 1;

        while (left < right) {
            answer = Math.max(answer, nums.get(left) + nums.get(right));
            ++left;
            --right;
        }

        return answer;
    }
}

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final ListNode head = new ListNode(-1);
        ListNode tail = head;

        for (int i = 0; i < n; ++i) {
            tail.next = new ListNode(scanner.nextInt());
            tail = tail.next;
        }

        System.out.println(new Solution().pairSum(head.next));

        scanner.close();
    }
}
