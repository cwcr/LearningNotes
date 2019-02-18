package leetcode43;

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		System.err.println(new Solution().multiply("9", "9"));
		System.err.println(2315*2323);
	}
	public String multiply(String num1, String num2) {
		int[] result1 = result(num1);
		int[] result2 = result(num2);
		//¶şÕßÏà³Ë
		List<List<Integer>> result = new ArrayList<>();
		int maxLength = 0;
		for (int i = result2.length-1; i >= 0; i--) {
			int erlang = 0;
			List<Integer> list = new ArrayList<>();
			for(int index = 0;index<result.size();index++) {
				list.add(0);
			}
			for (int j = result1.length-1; j >= 0; j--) {
				int k = result2[i]*result1[j] + erlang;
				erlang = k/10;
				list.add(k%10);
			}
			if(erlang>0) {
				list.add(erlang);
			}
			list = isZero(list);
			result.add(list);
			maxLength = Math.max(maxLength, list.size());
		}
		int erlang = 0;
		StringBuilder sBuilder  = new StringBuilder();
		for (int i = 0; i < maxLength; i++) {
			int now = erlang;
			for (List<Integer> list : result) {
				if(list.size()>=i+1) {
					now += list.get(i);
				}
			}
			sBuilder.append(now%10);
			erlang = now/10;
		}
		while (erlang > 0) {
			sBuilder.append(erlang%10);
			erlang = erlang/10;
		}
        return sBuilder.reverse().toString();
    }
	public int[] result(String nums) {
		char[] charArray = nums.toCharArray();
		int[] result = new int[charArray.length];
		for(int i=0;i<charArray.length;i++) {
			result[i] = charArray[i] - '0';
		}
		return result;
	}
	
	public List<Integer> isZero(List<Integer> list){
		for (Integer integer : list) {
			if(integer > 0) return list;
		}
		ArrayList<Integer> arrayList = new ArrayList<>();
		arrayList.add(0);
		return arrayList;
	}
	
	
}
