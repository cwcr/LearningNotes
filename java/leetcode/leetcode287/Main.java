package leetcode287;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		System.err.println(new Main().findDuplicate(new int[] {2,3,4,1,1}));
	}
	public int findDuplicate(int[] nums) {
		if(nums == null || nums.length == 0) {
			return 0;
		}
		for (int i = nums.length-1; i > 0; i--) {
			for(int j=0;j<i;j++) {
				if(nums[i]==nums[j]) {
					return nums[i];
				}
			}
		}
        return 0;
    }
}
