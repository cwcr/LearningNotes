package leetcode405;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	char[] hexs = new char[] {
			'0','1','2','3',
			'4','5','6','7',
			'8','9','a','b',
			'c','d','e','f'
	};
	
	public String toHex(int num) {
		long toHex;
		if(num<0) toHex = (long)Math.pow(2, 32)+num;
		else if(num ==0) return "0";
		else {
			toHex = num;
		}
		List<Character> list = new ArrayList<>();
        while (toHex > 0) {
			int index = (int)(toHex%16);
			toHex = toHex/16;
			list.add(hexs[index]);
		}
        return list.toString();
    }
	
//	public String toHex(int num) {
//		String result = new String();
//		while (num != 0) {
//			result = hexs[num & 0xf] + result;
//			num = num >>> 4;
//		}
//		return result.length() == 0 ? "0" : result;
//	}
	
	public static void main(String[] args) {
		System.err.println(new Solution().toHex(26));
		System.err.println(new Solution().toHex(-1));
	}
}
