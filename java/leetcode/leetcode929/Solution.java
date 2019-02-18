package leetcode929;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Solution {
	public static void main(String[] args) {
		String[] strings = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
		System.err.println(new Solution().numUniqueEmails(strings));
	}
	
    public int numUniqueEmails(String[] emails) {
    	HashSet<String> set = new HashSet<String>();
    	for (String string : emails) {
			set.add(this.checkMails(string));
		}
        return set.size();
    }
    
    private String checkMails(String mail) {
    	String[] split = mail.split("@");
    	if(split != null && split.length>1) {
    		String mailStart = split[0];
    		mailStart = mailStart.split("\\+")[0];
    		String[] split2 = mailStart.split("\\.");
    		return String.join("", split2).concat(split[1]);
    	}
    	return mail;
    }
}