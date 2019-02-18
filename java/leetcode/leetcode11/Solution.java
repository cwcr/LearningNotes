package leetcode11;

public class Solution {
	public static void main(String[] args) {
		System.err.println(new Solution().maxArea(new int[] {1,8,6,2,5,4,8,3,7}));
	}
	public int maxArea(int[] height) {
        int areaSize = 0;
        for (int i = 0; i < height.length-1; i++) {
			for (int j = i+1; j < height.length; j++) {
				areaSize = Math.max(Math.min(height[i], height[j])*(j-i),areaSize);
			}
		}
        return areaSize;
    }
}
