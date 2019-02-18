package leetcode26;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
	public static void main(String[] args) {
		int[] nums = new int[] {0,0,1,1,1,2,2,3,3,4};
		System.err.println(new Solution().removeDuplicates(nums));
		List<Integer> list = new ArrayList<Integer>();
		for (Integer integer : nums) {
			list.add(integer);
		}
		System.err.println(list);
	}
	public int removeDuplicates(int[] nums) {
		if(nums==null || nums.length==0) {
			return 0;
		}else {
			int result = nums.length-1;
			int i=1;
			for(;i<=result;i++) {
				if(nums[i] == nums[i-1]) {
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
