package leetcode94;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
	class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}
	
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		
		if(root == null) return result;
			
		if(root.left != null) {
			result.addAll(inorderTraversal(root.left));
		}
		result.add(root.val);
		if(root.right != null) {
			result.addAll(inorderTraversal(root.right));
		}
		
		return result;
    }
	
}
