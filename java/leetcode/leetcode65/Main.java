package leetcode65;

import java.math.BigDecimal;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		System.err.println(new Main().isNumber("3.5e+3.5e+3.5"));
	}
	
	public boolean isNumber(String s) {
		return isNumber(s.trim().toCharArray());
	}
	
	public boolean isNumber(char[] chars) {
		if(chars.length == 0) {return false;}
		int a = 0;
		if(chars[0] == '-'||chars[0] == '+') {
			return isNumber(Arrays.copyOfRange(chars, 1, chars.length));
		}else {
			int i = 0;
			if(chars[0] == '.') {
                if(chars.length>1&&chars[1]>='0'&&chars[1]<='9'){
                    a ++ ;
				    i ++ ;
                }else{
                    return false;
                }
			}
			for(;i<chars.length;i++) {
				if(chars[i] == '.') {
					a ++;
					if(a>=2) {return false;}
				}else if(chars[i] == 'e'||chars[i] == 'E') {
					if(i == 0) {return false;}
					return isExp(Arrays.copyOfRange(chars, i+1,chars.length));
				}else {
					if(chars[i]<'0'||chars[i]>'9') {return false;}
				}
			}
		}
		return true;
	}
	
	public boolean isExp(char[] chars) {
		if(chars.length == 0) {return false;}
		if(chars[0] == '-'||chars[0] == '+') {
			return isExp(Arrays.copyOfRange(chars, 1, chars.length));
		}else {
			for(int i = 0;i<chars.length;i++) {
				if(chars[i]<'0'||chars[i]>'9') {return false;}
			}
		}
		return true;
	}
}
