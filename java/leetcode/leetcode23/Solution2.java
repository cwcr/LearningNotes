package leetcode23;

//solution2 337 ms
public class Solution2 {
	public ListNode mergeKLists(ListNode[] lists) {
        if(lists != null&&lists.length==0) return null;
        if(lists.length == 1) return lists[0];
        ListNode head = new ListNode(0);
        for(int i = 0;i<lists.length;i++) {
        	head.next = mergeNode(head.next, lists[i]);
        }
        return head.next;
	}
	
	public ListNode mergeNode(ListNode node1,ListNode node2) {
		if(node1 == null) return node2;
		if(node2 == null) return node1;
		ListNode head = new ListNode(0);
		ListNode next = head;
		while(node1 != null || node2 != null) {
			if(node1 == null) {next.next = node2;node2 = null;break;}
			if(node2 == null) {next.next = node1;node1 = null;break;}
			if(node1.val < node2.val) {
				next.next = new ListNode(node1.val);
				node1 = node1.next;
			}else {
				next.next = new ListNode(node2.val);
				node2 = node2.next;
			}
			next = next.next;
		}
		return head.next;
	}
}
