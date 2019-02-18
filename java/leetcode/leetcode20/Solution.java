package leetcode20;

import java.util.Arrays;
import java.util.List;

public class Solution {
	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] findSmall = solution.findSmall("()", new String[] {"(",")"});
		System.err.println("()".substring(findSmall[0], findSmall[1]+1));
		System.err.println(solution.isValid("()"));
	}
	public boolean isValid(String s) {
        if(s.length() == 0) return true;
        if(s.length()%2 == 1) {System.err.println(s);return false;}
        List<String[]> list = Arrays.asList(new String[] {"[","]"},
        		new String[] {"(",")"},
        		new String[] {"{","}"});
        boolean flag = true;
        for (int i = 0; i < list.size(); i++) {
			int[] findSmall = findSmall(s, list.get(i));
			//flag = flag&&checkOut(s,list.get(i));
			if(findSmall[0] == -1 && findSmall[1] == -1) {
				flag = flag&&true;
			}else if(findSmall[0] >= 0 && findSmall[1] >= 0 && findSmall[0]<findSmall[1]) {
				flag = flag&&isValid(s.substring(findSmall[0]+1, findSmall[1]));
			}else {
				flag = flag&&false;
			}
		}
        return flag;
    }
	
	int[] findSmall(String s,String[] sts) {
		char[] charArray = s.toCharArray();
		int index = -1,lastIndex = -1;
		int startCount = 0,endCount = 0;
		char startChar = sts[0].toCharArray()[0],endChar = sts[1].toCharArray()[0];
		for (int i = 0; i < charArray.length; i++) {
			if(startChar == charArray[i]) {
                startCount ++;
				if(lastIndex == -1) {
					index = i;
				}
			}else if(endChar == charArray[i]) {
				endCount ++;
				if(lastIndex == -1) {
					lastIndex = i;
				}
			}
		}
		if(startCount == endCount) {
			return new int[] {index,lastIndex};
		}else {
			return new int[] {0,-1};
		}
	}
}
