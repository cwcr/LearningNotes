package leetcode24;

public class Solution {
	public ListNode swapPairs(ListNode head) {
       ListNode headNode = new ListNode(0);
       headNode.next = head;
       ListNode next = headNode;
       ListNode first,second;
       while (next.next != null&&next.next.next != null) {//½»»»½áÊø
			first = next.next;
			second = first.next;
			first.next = second.next;
			second.next = first;
			next.next = second;
			next = first;
       }
       return headNode.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
