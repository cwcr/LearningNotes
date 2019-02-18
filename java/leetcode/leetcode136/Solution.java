package leetcode136;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public static void main(String[] args) {
		int nums[] = {1,2,3,2,3};
		System.err.println(new Solution().singleNumber(nums));
	}
	public int singleNumber(int[] nums) {
		int result = nums[0];
		for (int i = 1; i < nums.length; i++) {
			result^=nums[i];
		}
		return result;
    }
}
