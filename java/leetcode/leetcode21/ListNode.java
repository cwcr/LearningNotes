package leetcode21;


//Definition for singly-linked list.
public class ListNode {
  int val;
  ListNode next;
  ListNode(int x) { val = x; }
  @Override
  public String toString() {
  	StringBuilder sBuilder = new StringBuilder();
  	sBuilder.append(val);
  	ListNode nextNode = next;
  	while (nextNode != null) {
  		sBuilder.append(nextNode.val);
  		nextNode = nextNode.next;
		}
  	return sBuilder.toString();
  }
}

