package leetcode27;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public static void main(String[] args) {
		int[] nums = new int[] {0,0,1,1,1,2,2,3,3,4};
		System.err.println(new Solution().removeElement(nums,1));
		List<Integer> list = new ArrayList<Integer>();
		for (Integer integer : nums) {
			list.add(integer);
		}
		System.err.println(list);
	}
	
	public int removeElement(int[] nums, int val) {
		if(nums==null || nums.length==0) {
			return 0;
		}else {
			int result = nums.length-1;
			int i=0;
			for(;i<=result;i++) {
				if(nums[i] == val) {
					updateSize(nums, i);
					result-=1;
					i--;
				}
			}
			return i;
		}
	}
	
	void updateSize(int[] nums,int index){
		int amp = nums[index];
		for(;index<nums.length-1;index++) {
			nums[index] = nums[index+1];
		}
		nums[nums.length-1] = amp;
	}
}
