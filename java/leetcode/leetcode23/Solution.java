package leetcode23;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * solution ³¬Ê±
 * @author ye
 *
 */
public class Solution {
	public ListNode mergeKLists(ListNode[] lists) {
        List<ListNode> nodes = checkAndToList(lists);
        ListNode head = new ListNode(0);
        ListNode now = head,next;
        while (nodes!=null&&nodes.size()>0) {
			next = minList(nodes);
			now.next = next;
			now = next;
		}
        return head.next;
    }
	
	List<ListNode> checkAndToList(ListNode[] lists){
		List<ListNode> nodes = Arrays.stream(lists).collect(Collectors.toList());
		for (int i = 0; i < nodes.size(); i++) {
			if(nodes.get(i) == null) nodes.remove(i);
		}
		return nodes;
	}
	
	ListNode minList(List<ListNode> nodes) {
		int minIndex = 0;
		if(nodes == null||nodes.size() == 0) return null;
		else if(nodes.size() == 1) {
			return getAndRemoveNode(nodes,0);
		}
		for (int i = 0; i < nodes.size(); i++) {
			if(nodes.get(minIndex).val > nodes.get(i).val) minIndex = i;
		}
		return getAndRemoveNode(nodes, minIndex);
	}
	
	ListNode getAndRemoveNode(List<ListNode> nodes,int index){
		if(nodes.size()<index+1) return null;
		ListNode result = nodes.get(index);
		nodes.set(index, nodes.get(index).next);
		if(nodes.get(index) == null) {
			nodes.remove(index);
		}
		return result;
	}
	
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}