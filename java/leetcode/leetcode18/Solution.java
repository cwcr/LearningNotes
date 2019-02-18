package leetcode18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	public static void main(String[] args) {
		System.err.println(new Solution().fourSum(new int[] {1, 0, -1, 0, -2, 2}, 0));
		System.err.println(new Solution().fourSum(new int[] {0,0,0,0}, 0));
		System.err.println(new Solution().fourSum(new int[] {-1,-5,-5,-3,2,5,0,4}, -7));
	}
	public List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<>();
		for(int i=0;i<nums.length-3;i++) {
			List<List<Integer>> three = threeSum(Arrays.copyOfRange(nums, i+1, nums.length), target-nums[i],nums[i]);
			for(List dto:three) {
				if(!result.contains(dto)) {
					result.add(dto);
				}
			}
		}
		return result;
    }
	
	public List<List<Integer>> threeSum(int[] nums,int target,int a) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums != null&& nums.length>=3) {
        	List<Integer> firstList = null;
        	for (int i = 0; i < nums.length-2; i++) {
        		if(i>0&&nums[i]==nums[i-1]) continue;
				for (int j = i+1; j < nums.length-1; j++) {
					if(Arrays.binarySearch(nums, j+1, nums.length, target-nums[i]-nums[j])>0) {
						List<Integer> asList = Arrays.asList(nums[i],nums[j],target-nums[i]-nums[j],a);
						if(!asList.equals(firstList)) {
							result.add(asList);
							firstList = asList;
						}
					}
				}
			}
        }
        return result;
    }
}
