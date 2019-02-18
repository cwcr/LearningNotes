package leetcode70;

public class Solution {
	public static void main(String[] args) {
		System.err.println(new Solution().climbStairs(4));
	}
	public int climbStairs(int n) {
        if(n==1 || n==2) return n;
        if(n>2) {
        	int a=1,b=2,c;
        	for(int i=2;i<n;i++) {
        		c = a+b;
        		a=b;b=c;
        	}
        	return b;
        }
        return 0;
    }
}
