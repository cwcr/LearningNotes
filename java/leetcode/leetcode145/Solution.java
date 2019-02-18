package leetcode145;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}
	 public List<Integer> postorderTraversal(TreeNode root) {
		 List<Integer> result = new ArrayList<>();
			
			if(root == null) return result;
				
			if(root.left != null) {
				result.addAll(postorderTraversal(root.left));
			}
			if(root.right != null) {
				result.addAll(postorderTraversal(root.right));
			}
			result.add(root.val);
			
			return result;
	 }
}
