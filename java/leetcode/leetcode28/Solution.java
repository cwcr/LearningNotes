package leetcode28;

public class Solution {
	public static void main(String[] args) {
		System.err.println(new Solution().strStr("aaa",
				"aaa"));
	}
	
	public int strStr(String haystack, String needle) {
        if(needle == null || needle.length() == 0) {
        	return 0;
        }else if(haystack.length()<needle.length()) {
			return -1;
		}else {
			int i = 0;
			for(;i<haystack.length() - needle.length()+1;i++) {
				if(haystack.charAt(i) == needle.charAt(0)&&isStr(haystack.substring(i, haystack.length()),needle)) {
					return i;
				}
			}
		}
        return -1;
    }
	
	boolean isStr(String a,String b) {
		for(int i = 1;i<b.length();i++) {
			if(a.charAt(i) 
					
					!= b.charAt(i)) return false;
		}
		return true;
	}
}
