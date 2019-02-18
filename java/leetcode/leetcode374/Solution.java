package leetcode374;

public class Solution {
	public int guessNumber(int n) {
        return guessNumber(1,n);
    }
    
    public int guessNumber(int start,int end) {
        int next = (int)(((long)start+(long)end)/2);
        System.err.println(next);
        if(next ==  start && next<end) next ++;
        if(next == end && next> start) next --;
        if(guess(next) == 0){
            return next;
        }else if(guess(next) == 1){
            return guessNumber(next+1,end);
        }else{
            return guessNumber(start,next-1);
        }
    }
    
    public int guess(int next) {
    	int pick = 1702766719;
    	if(next > pick) return -1;
    	else if(next == pick) return 0 ;
    	else return 1;
    }
    
    public static void main(String[] args) {
		System.err.println(new Solution().guessNumber(2126753390));
	}
}
