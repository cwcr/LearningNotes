package leetCode50;

public class Solution {
	public static void main(String[] args) {
		System.err.println(new Solution().myPow(2, 4));
	}
	
//	public double myPow(double x, int n) {
//		return Math.pow(x, (double)n);
//	}
	
	public double myPow(double x,int n) {
		if(x == 0) return 0;
		if(n == 0) {
			return 1;
		}
		double result = x;
		if(x>0) {
			for(int i=1;i<n;i++) {
				result *= x;
			}
		}else {
			result = 1/x;
			for(int i=-1;i>n;i--) {
				result *= 1/x;
			}
		}
		return result;
	}
}
