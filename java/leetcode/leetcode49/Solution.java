package leetcode49;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Solution {
	void showArray(int[] height) {
		ArrayList<Integer> arrayList = new ArrayList<>();
		for (int i : height) {
			arrayList.add(i);
		}
		System.err.println(arrayList);
	}
	public static void main(String[] args) {
		System.err.println(new Solution().trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));//6
		System.err.println(new Solution().trap(new int[] {2,0,2}));//2
	}
	public int trap(int[] height) {
        int size = 0;
        if(height != null && height.length>0) {
        	int index = findMax(height);//ÖÐ¼ä
        	if(index > 0) {
        		size+=trapLeft(Arrays.copyOfRange(height, 0, index));
        	}
        	size+=trapRight(Arrays.copyOfRange(height, index+1, height.length));
        }
        return size;
    }
	
	int trapLeft(int[] height) {
		int size = 0;
		if(height != null && height.length>0) {
			int index = findMax(height);
			if(index < height.length -1) {
				for(int i=index;i<height.length;i++) {
					size+=height[index]-height[i];
				}
			}
			size += trapLeft(Arrays.copyOfRange(height, 0, index));
		}
		return size;
	}
	
	int trapRight(int[] height) {
		int size = 0;
		if(height != null && height.length>0) {
			int index = findMax(height);
			if(index > 0) {
				for(int i=index;i>=0;i--) {
					size+=height[index]-height[i];
				}
			}
			size += trapRight(Arrays.copyOfRange(height, index+1, height.length));
		}
		return size;
	}
	
	int findMax(int[] height) {
		int index  =0;
		for (int i = 1; i < height.length; i++) {
			if(height[index]<height[i]) index = i;
		}
		return index;
	}
}
