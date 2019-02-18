package leetcode5;

import java.util.Vector;

public class Solution {
	public String longestPalindrome(String s) {
		char[] chars = s.toCharArray();
		char specilaChar = '$';
		int size = chars.length * 2 + 1;
		char[] schars = new char[size];
		for (int i = 0; i < size - 1; i += 2) {
			schars[i] = specilaChar;
			schars[i + 1] = chars[i / 2];
		}
		schars[size - 1] = specilaChar;

		int center = -1;int maxLength =0;
		
		//时间复杂度 O^2解法
		for (int i = 1; i < size; i++) {
			int length = 0;
			for(;length<(size+1)/2;length++) {
				if(i-length<0) break;
				if(i+length>=size) break;
				if(schars[i-length] != schars[i+length]) break;
			}
			if(maxLength < length) {
				maxLength = length;
				center = i;
			}
		}
		if(center == -1) {
			return "";
		}
		System.err.println(center);
		System.err.println(maxLength);
		return s.substring((center-maxLength+1)/2,(center/2+maxLength/2));
	}

	public static void main(String[] args) {
		System.err.println(new Solution().longestPalindrome("cbbd"));
		show((Object)null);
	}

	public void showChars(char[] cs) {
		for (char c : cs) {
			System.err.print(c);
		}
		System.err.println();
	}
	
	public static void show(String s) {
		System.err.println("S");
	}
	
	public static void show(Object s) {
		System.err.println("o");
	}
	
	public static void show(Integer s) {
		System.err.println("c");
	}
}
