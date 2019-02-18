package leetcode771;

public class Solution {
	public static void main(String[] args) {
		System.err.println(new Solution().numJewelsInStones("Az", "AaaaZzzAsEsa"));
	}
	public int numJewelsInStones(String J, String S) {
		int result = 0;
		char[] charArray = S.toCharArray();
		for (char c : charArray) {
			if(J.contains(String.valueOf(c))) {
				result++;
			}
		}
        return result;
    }
}
