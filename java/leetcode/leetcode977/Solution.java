package leetcode977;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        System.out.println(11);
    }
    public int[] sortedSquares(int[] A) {
    	for(int i=0;i<A.length;i++) {
    		A[i]*=A[i];
    	}
        Arrays.sort(A);
        return A;
    }
}
