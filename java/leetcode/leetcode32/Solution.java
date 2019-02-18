package leetcode32;

public class Solution {
	public static void main(String[] args) {
		System.err.println(new Solution().longestValidParentheses(")()())"));
	}
	
	public int longestValidParentheses(String s) {
        s = findMaxString(s);
        char[] chars = s.toCharArray();
        int result  = 0;
        for(int i = 0;i<chars.length-1;i++) {
        	for(int j = chars.length-1;j>i;j--) {
        		if(chars[i] == '(' && chars[j] == ')') {
        			result ++;
        		}
        	}
        }
        return result;
    }
	
	String findMaxString(String s) {
		int indexOf = s.indexOf("(");
        int lastIndexOf = s.lastIndexOf(")");
        if(indexOf >=0 && lastIndexOf>=0 && lastIndexOf>indexOf) {
        	return s.substring(indexOf, lastIndexOf+1);
        }
        return "";
	}
}
