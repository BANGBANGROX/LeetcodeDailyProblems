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
    public ListNode rotateRight(final ListNode head, final int k) {
        final int listLength = getLength(head);

        if (listLength == 0) {
            return head;
        }

        final int rotationsNeeded = k % listLength;

        if (rotationsNeeded == 0) {
            return head;
        }

        final int startIdx = listLength - rotationsNeeded;
        int idx = 0;
        ListNode current = head;
        final ListNode tail = getTail(head);

        while (idx < startIdx - 1) {
            current = current.next;
            ++idx;
        }

        final ListNode next = current.next;
        current.next = null;
        tail.next = head;

        return next;
    }

    private int getLength(final ListNode head) {
        int len = 0;
        ListNode current = head;

        while (current != null) {
            ++len;
            current = current.next;
        }

        return len;
    }

    private ListNode getTail(final ListNode head) {
        ListNode current = head;

        while (current.next != null) {
            current = current.next;
        }

        return current;
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

        final int k = scanner.nextInt();

        ListNode current = new Solution().rotateRight(head, k);

        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }

        System.out.println();

        scanner.close();
    }
}
