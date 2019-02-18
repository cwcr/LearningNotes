package leetcode17;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	public static void main(String[] args) {
		System.err.println(new Solution().letterCombinations(""));
	}
	
	public Map<Character, List<String>> maps = new HashMap<>();
	{
		maps.put('2', Arrays.asList(new String[] {"a","b","c"}));
		maps.put('3', Arrays.asList(new String[] {"d","e","f"}));
		maps.put('4', Arrays.asList(new String[] {"g","h","i"}));
		maps.put('5', Arrays.asList(new String[] {"j","k","l"}));
		maps.put('6', Arrays.asList(new String[] {"m","n","o"}));
		maps.put('7', Arrays.asList(new String[] {"p","q","r","s"}));
		maps.put('8', Arrays.asList(new String[] {"t","u","v"}));
		maps.put('9', Arrays.asList(new String[] {"x","w","y","z"}));
	}
    public List<String> letterCombinations(String digits) {
        char[] charArray = digits.toCharArray();
        List<List<String>> list = new ArrayList<>();
        for (char c : charArray) {
			list.add(maps.get(c));
		}
        return letterCombinations(list);
    }
    
    private List<String> letterCombinations(List<List<String>> chars){
		switch (chars.size()) {
		case 0:
			return null;
		case 1:
			return chars.get(0);
		case 2:
			List<String> result = new ArrayList<>();
			List<String> aList = chars.get(0);
			List<String> bList = chars.get(1);
			for (String a : aList) {
				for (String b : bList) {
					result.add(a+b);
				}
			}
			return result;
		default: //>2
			return letterCombinations(Arrays.asList(chars.get(0),letterCombinations(chars.subList(1, chars.size()))));
		}
    }
}
