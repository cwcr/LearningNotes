package leetcode7;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		System.err.println(new Main().reverse(-2147483648));
	}
	
	public int reverse(int x) {
        String s = new Integer(x).toString();
        char[] charArray = s.toCharArray();
        char c = charArray[0];
        try {
			Integer i = new Integer(new Character(c).toString());
        	return reverse(charArray);
        }catch (Exception e) {
        	charArray = Arrays.copyOfRange(charArray, 1, charArray.length);
        	switch (c) {
			case '-':
				return 0-reverse(charArray);
			case '+':
				reverse(charArray);
			default:
				return 0;
			}
		}
        
    }
	
	private int reverse(char[] chars) {
        try{
		    return new Integer(new String(reverseChar(chars)));
        }catch(Exception e){
            return 0;
        }
	}
	
	private char[] reverseChar(char[] chars) {
		char[] result= new char[chars.length];
		for(int i = chars.length-1;i>=0;i--) {
			result[chars.length-i-1] = chars[i];
		}
		return result;
	}
}
