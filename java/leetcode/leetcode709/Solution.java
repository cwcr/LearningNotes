package leetcode709;

import java.awt.datatransfer.StringSelection;

public class Solution {
	public static void main(String[] args) {
		System.err.println((int)'A');
		System.err.println((int)'a');
		System.err.println((int)'Z');
		System.err.println(new Solution().toLowerCase("NyChsa"));
	}
	
	
	public String toLowerCase(String str) {
		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			int c = charArray[i];
			if(65<=c&&c<=90) {
				charArray[i] = (char) (c+32);
			}
		}

		return String.valueOf(charArray);
    }
}
