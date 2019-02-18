package leetcode21;

public class Solution {
	public static void main(String[] args) {
		ListNode head1 = new ListNode(1);
		head1.next = new ListNode(2);
		head1.next.next = new ListNode(3);
		
		ListNode head2 = new ListNode(1);
		head2.next = new ListNode(3);
		head2.next.next = new ListNode(4);
		
		System.err.println(new Solution().mergeTwoLists(head1, head2));
	}
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        else if(l2 == null) return l1;
        else {
			ListNode head = new ListNode(0);
			ListNode now;
			if(l1.val < l2.val) {
				now = new ListNode(l1.val);
				l1 = l1.next;
			}else {
				now = new ListNode(l2.val);
				l2 = l2.next;
			}
			head.next = now;
			while (true) {
				if(l1 == null) {
					now.next = l2;break;
				}else if(l2 == null) {
					now.next = l1;break;
				}else {
					if(l1.val < l2.val) {
						now.next = new ListNode(l1.val);
						l1 = l1.next;
					}else {
						now.next = new ListNode(l2.val);
						l2 = l2.next;
					}
					now = now.next;
				}
			}
			return head.next;
		}
    }
}
