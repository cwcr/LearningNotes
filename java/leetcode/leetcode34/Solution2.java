package leetcode34;

import java.util.Arrays;

public class Solution2 {
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
		show(new Solution2().searchRange(new int[] {1,2,2,2,2,2,3}, 1));
		show(new Solution2().searchRange(new int[] {5,7,7,8,8,10}, 8));
	}
	public int[] searchRange(int[] nums, int target) {
		int start = Arrays.binarySearch(nums, target);
		if(start<0) return new int[] {-1,-1};
		else {
			int[] result = new int[] {start,start};
			while (start<nums.length-1) {//Íù´óÕÒ
				start ++;
				if(nums[start] != target) {
					break;
				}
				result[1] = start;
			}
			start = result[0];
			while (start>0) {//ÍùĞ¡ÕÒ
				start -= 1;
				if(nums[start] != target) {
					break;
				}
				result[0] = start;
			}
			return result;
		}
	}
}
