package leetcode14;

public class Solution {
	public static void main(String[] args) {
		System.err.println(new Solution().longestCommonPrefix(new String[] {"flower","flow","flight"}));
		System.err.println(new Solution().longestCommonPrefix(new String[] {"baab","bacb","b","cbc"}));
	}
	
	public String longestCommonPrefix(String[] strs) {
        if(strs == null||strs.length == 0) return "";
        else if(strs.length == 1) return strs[0];
        else {
        	String longestCommonPrefix = longestCommonPrefix(strs[0], strs[1]);
        	for(int i = 2;i<strs.length;i++) {
        		longestCommonPrefix = longestCommonPrefix(longestCommonPrefix,strs[i]);
        	}
        	return longestCommonPrefix;
        }
    }
	
	public String longestCommonPrefix(String string1,String string2) {
		int min = Math.min(string1.length(), string2.length());
		StringBuilder sBuilder = new StringBuilder("");
		for(int i = 0;i<min;i++) {
			if(string1.charAt(i)==string2.charAt(i)){
				sBuilder.append(string1.charAt(i));
			}else {
				break;
			}
		}
		return sBuilder.toString();
	}
}
