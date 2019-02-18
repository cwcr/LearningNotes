package leetcode18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {
	public static void main(String[] args) {
		System.err.println(new Solution().fourSum(new int[] {1, 0, -1, 0, -2, 2}, 0));
		System.err.println(new Solution().fourSum(new int[] {0,0,0,0}, 0));
		System.err.println(new Solution().fourSum(new int[] {-1,-5,-5,-3,2,5,0,4}, -7));
		System.err.println(new Solution().fourSum(new int[] {-3,-2,-1,0,0,1,2,3}, 0));
	}
	
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
        if(nums != null&& nums.length>=4) {
        	Arrays.sort(nums);
        	for (int i = 0; i < nums.length-3; i++) {
				for (int j = i+1; j < nums.length-2; j++) {
					for(int k = j+1; k<nums.length-1;k++) {
						if(Arrays.binarySearch(nums, k+1, nums.length, target-nums[i]-nums[j]-nums[k])>0) {
							List<Integer> asList = Arrays.asList(nums[i],nums[j],nums[k],target-nums[i]-nums[j]-nums[k]);
							if(!asList.contains(asList)) {
								result.add(asList);
							}
						}
					}
				}
			}
        }
        return result;
    }
	
}
