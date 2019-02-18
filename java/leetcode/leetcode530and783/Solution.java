package leetcode530and783;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	public static void main(String[] args) {
		TreeNode start = new Solution().new TreeNode(1);
		start.right = new Solution().new TreeNode(3);
		start.right.left = new Solution().new TreeNode(2);
		System.err.println(new Solution().getMinimumDifference(start));
	}

//	public int minDiffInBST(TreeNode root) {
//		if(root  == null) return 0;
//		int leftHeight = -1;
//		int rightHeight = -1;
//        if(root.left != null) {
//        	leftHeight = min(getMinimumDifference(root.left),Math.abs(root.left.val - root.val));
//        }
//        if(root.right != null) {
//        	rightHeight = min(getMinimumDifference(root.right),Math.abs(root.right.val - root.val));
//        }
//        return min(leftHeight, rightHeight);
//    }
//	
//	private int min(int a,int b) {
//		if(a == -1) return b;
//		if(b == -1) return a;
//		return Math.min(a, b);
//	}
	
	public int getMinimumDifference(TreeNode root) {
		Set<Integer> set = getSet(root);
		Integer[] a = new Integer[set.size()];
		a = set.toArray(a);
		Arrays.sort(a);
		int min = Integer.MAX_VALUE;
		for (int i = 1; i < a.length; i++) {
			min = Math.min(min, a[i]-a[i-1]);
		}
		return min;
	}
	
	public Set<Integer> getSet(TreeNode node){
		Set<Integer> result = new HashSet<>();
		result.add(node.val);
		
		if(node.left != null) {
			result.addAll(getSet(node.left));
		}
		if(node.right != null) {
			result.addAll(getSet(node.right));
		}
		
		return result;
	}
}
