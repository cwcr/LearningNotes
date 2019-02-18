package leetcode8;

import java.math.BigDecimal;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		System.err.println(new Main().myAtoi("  0000000000012345678"));
	}
	
	public int myAtoi(String str) {
		char[] nums = new char[]{'0','1','2','3','4','5','6','7','8','9'};
        str = str.trim();
        char[] chars = str.toCharArray();
        try {
        	if(chars.length>0&&(chars[0] == '-'||chars[0] == '+'||Arrays.binarySearch(nums, chars[0])>=0)) {//存在数组中
	        	int i = 1;
	        	for(;i<chars.length;i++) {
	        		if(Arrays.binarySearch(nums, chars[i])<0) {
	        			break;
	        		}
	        	}
	        	chars = Arrays.copyOfRange(chars, 0, i);
	    		BigDecimal bigDecimal = new BigDecimal(new String(chars));
	    		return bigDecimal.compareTo(new BigDecimal(Integer.MAX_VALUE))>=0?Integer.MAX_VALUE:
	        		bigDecimal.compareTo(new BigDecimal(Integer.MIN_VALUE))<=0?Integer.MIN_VALUE:bigDecimal.intValue();
	        }else {
	        	return 0;
	        }
        }catch (Exception e) {
        	e.printStackTrace();
			return 0;
		}
    }
}
