package leetcode375;

public class Solution {
	public int getMoneyAmount(int n) {
        return guessNumber(0, n, n-1);
    }
    
    public int guessNumber(int start,int end, int pick) {
        int next = (int)(((long)start+(long)end+1)/2);
        if(next ==  start && next<end) next ++;
        if(next == end && next> start) next --;
        if(guess(next,pick) == 0){
            return next;
        }else if(guess(next,pick) == 1){
            return next + guessNumber(next+1,end,pick);
        }else{
            return next + guessNumber(start,next-1,pick);
        }
    }
    
    
    public int guess(int next,int pick) {
    	if(next > pick) return -1;
    	else if(next == pick) return 0 ;
    	else return 1;
    }
    
    public static void main(String[] args) {
		System.err.println(new Solution().getMoneyAmount(10));
	}
}
