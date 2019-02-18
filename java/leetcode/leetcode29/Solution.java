package leetcode29;

public class Solution {
	public static void main(String[] args) {
		//System.err.println(1>>2);
		//System.err.println(new Solution().divide(1, 1));
		//System.err.println(new Solution().divide(-7, -3));
		//System.err.println(new Solution().divide(-7, 3));
		System.err.println(new Solution().divide(Integer.MIN_VALUE, -1));
	}
	
	public int divide(int dividend, int divisor) {
		return divide(Math.abs((long)dividend), Math.abs((long)divisor), (dividend>0&&divisor>0)||(dividend<0&&divisor<0));
	}
	
	public int divide(long dividend, long divisor,boolean flag) {
		long result = 0;
		long nowDivisor = divisor;
		int step = 0;
		while (dividend>nowDivisor) {
			nowDivisor <<= 1;
			step++;
		}
		while (nowDivisor >= divisor) {
			if(dividend-nowDivisor >= 0) {
				dividend -= nowDivisor;
				result += (long)1<<step;
			}
			nowDivisor>>=1;
			step -- ;
		}
		return exceptionResult(result, flag);
	}
	
	public int exceptionResult(long result,boolean flag) {
		if(flag) {
			return (int)Math.min((long)Integer.MAX_VALUE, result);
		}else {
			return (int)Math.max((long)Integer.MIN_VALUE, 0-result);
		}
	}
}
