package leetcode46;

import java.util.*;

public class Solution {
	 public static void main(String[] args) {
		System.err.println(new Solution().permute(new int[] {0,-1,1}));
		ArrayList arrayList = new ArrayList();
		arrayList.add(1);
		arrayList.add(2);
		arrayList.add(3);
		System.err.println(new Solution().permute(arrayList));
	}
	 public List<List<Integer>> permute(int[] nums) {
		 int length = nums.length;
		 List<Integer> list = new ArrayList<Integer>();
		 for (Integer integer : nums) {
			list.add(integer);
		}
		 return permute(list);
	 }
	 
	 public List<List<Integer>> permute(List<Integer> nums) {
		 int length = nums.size();
		 List<List<Integer>> list = new ArrayList<List<Integer>>();
		 if(length == 1) {
			 for (Integer i : nums) {
				ArrayList<Integer> arrayList = new ArrayList<>();
				arrayList.add(i);
				list.add(arrayList);
			}
		 }else {
			 for (int i = 0; i < nums.size(); i++) {
				Integer index = nums.get(i);
				nums.remove(i);
				List<List<Integer>> permute = permute(nums);
				nums.add(i, index);
				for (List<Integer> list2 : permute) {
					list2.add(index);
					list.add(list2);
				}
			}
		 }
		 return list;
	 }
}
