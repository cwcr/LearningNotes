package leetcode6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		System.err.println(new Main().convert("PAYPALISHIRING", 3));
	}
	
	public String convert(String s, int numRows) {
        List<List<String>> list =new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
			list.add(new ArrayList<String>());
		}
        char[] charArray = s.toCharArray();
        int index = 0;
        for (int i = 0; i < charArray.length; i++) {
        	if(index%numRows == 0) {
				for (List<String> sList : list) {
					if(i<charArray.length) {
						sList.add(String.valueOf(charArray[i]));
						i++;
					}else {
						break;
					}
				}
				i--;
			}else {
				list.get(numRows - 1 - (index%numRows)).add(String.valueOf(charArray[i]));
			}
			index++;
			if(index%numRows == numRows-1) {
				index++;
			}
		}
        StringBuilder sBuilder = new StringBuilder();
        for (List<String> l : list) {
			for (String string : l) {
				sBuilder.append(string);
			}
		}
        return sBuilder.toString();
    }
}
