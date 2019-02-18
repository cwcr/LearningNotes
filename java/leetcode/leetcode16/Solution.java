package leetcode16;

import java.util.Arrays;

public class Solution {
	public static void main(String[] args) {
		System.err.println(new Solution().threeSumClosest(new int[] {1,1,-1,-1,3}, 3));
	}
	
	 public int threeSumClosest(int[] nums, int target) {
		 int result = Integer.MAX_VALUE;
	     if(nums != null&& nums.length>=3) {
	    	 Arrays.sort(nums);
	    	 result = nums[0]+nums[1]+nums[2];
	    	 for (int i = 0; i < nums.length-2; i++) {
	    		//if(Math.abs(nums[i])>Math.abs(target - result)) break;
				for (int j = i+1; j < nums.length-1; j++) {
					//if(Math.abs(nums[j]+nums[i])>Math.abs(target - result)) break;
					for (int j2 = j+1; j2 < nums.length; j2++) {
						//if(Math.abs(nums[j]+nums[i]+nums[j2])>Math.abs(target - result)) break;
						int thisResult =  nums[i]+nums[j]+nums[j2];
						if(Math.abs(target - result) > Math.abs(target - thisResult)) {
							result = thisResult;
						}
					}
				}
			}
	     }
	     return result;
	 }
}
