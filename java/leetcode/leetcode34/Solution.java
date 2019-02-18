package leetcode34;

import java.util.Arrays;

public class Solution {
	public static void show(int[] result) {
		System.err.print("[");
		for (int i = 0; i < result.length; i++) {
			System.err.print(result[i]+" ");
		}
		System.err.print("]");
		System.err.println();
	}
	public static void main(String[] args) {
		//show(Arrays.copyOf(new int[] {1,2,2,2,2,2,3}, 3));
		show(new Solution().searchRange(new int[] {1,2,2,2,2,2,3}, 1));
		show(new Solution().searchRange(new int[] {5,7,7,8,8,10}, 6));
	}
	public int[] searchRange(int[] nums, int target) {
		int length = nums.length;
		int[] result = new int[] {-1,-1};
		int medium;
		if(length > 0) {
			int now = length/2;
			medium = now;
			if(nums[now] == target) {//处理中位数情况
				int tnow = now;
				while (tnow-1>=0&&nums[tnow - 1] == target) {
					tnow = tnow-1;
				}
				result[0] = tnow;
				tnow = now;
				while (tnow+1<length&&nums[tnow + 1] == target) {
					tnow = tnow+1;
				}
				result[1] = tnow;
			}else {
				if(length>1) {
					if(nums[now] > target) {
						return searchRange(Arrays.copyOf(nums, now),target);
					}else {
						result = searchRange(Arrays.copyOfRange(nums, now, length),target);
						if(result[0]>0) {
							result[0] = result[0]+medium;
							result[1] = result[1]+medium;
						}
					}	
				}
			}
		}
        return result;
    }
}
