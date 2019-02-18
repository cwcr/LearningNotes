package leetcode19;

public class Solution {
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		System.err.println(head);
		System.err.println(new Solution().removeNthFromEnd(head,1));
	}
	public ListNode removeNthFromEnd(ListNode head, int n) {
		if(head == null) {
			return null;
		}
		ListNode node = new ListNode(0);//添加一个头部节点，防止空指针
		node.next = head;
		ListNode h = node;
		ListNode now = node;
		for(int i = 0;i<n+1;i++) {
			if(now == null) {
				return head;
			}else {
				now = now.next;
			}
		}
		while (now != null) {
			now = now.next;
			h = h.next;
		}
		h.next = h.next.next;
        return node.next;
    }
}
