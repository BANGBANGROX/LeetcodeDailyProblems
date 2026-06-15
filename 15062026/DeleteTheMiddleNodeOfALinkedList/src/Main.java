import java.util.Scanner;

class ListNode {
    int val;
    ListNode next;

    public ListNode(final int val) {
        this.val = val;
    }
}

class Solution {
    public ListNode deleteMiddle(final ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode previous = null;

        while (fast != null && fast.next != null) {
            previous = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        if (slow == null || previous == null) {
            return null;
        }

        previous.next = slow.next;

        return head;
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

        final ListNode answer = new Solution().deleteMiddle(head.next);
        print(answer);
        System.out.println();

        scanner.close();
    }

    private static void print(final ListNode head) {
        if (head == null) {
            return;
        }

        System.out.print(head.val);

        print(head.next);
    }
}
