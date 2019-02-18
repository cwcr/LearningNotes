package leetcode41;

import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		System.err.println(new Main().firstMissingPositive(new int[] {7,8,9,11,12}));
	}
	 public int firstMissingPositive(int[] nums) {
		 if(nums == null || nums.length == 0) {
			 return 1;
		 }
		 Map<Integer, Integer> map = new HashMap<>();
		 for (int i : nums) {
			if(i>0) {
				map.put(i, 1);
			}
		}
		 for(int i = 1;;i++) {
			 if(map.get(i) == null) {
				 return i;
			 }
		 }
	 }
}
