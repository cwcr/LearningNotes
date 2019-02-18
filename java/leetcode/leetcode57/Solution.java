package leetcode57;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		List<Interval> rerult = new ArrayList<>();
        if(newInterval != null && intervals != null) {
        	boolean insertFlag = false;
        	boolean newFlag = intervals != null;
        	Interval startInterval = null;
        	for (int i = 0; i < intervals.size(); i++) {
    			Interval endInterval = intervals.get(i);
				if(insertFlag) {
    				rerult.add(endInterval);
    			}else {
    				if(newFlag) {
    					if(newInterval.start <= endInterval.start) {
    						endInterval = newInterval;
    						i--;
    						newFlag = false;
    					}
    				}
    				if(startInterval == null) {
    					startInterval = endInterval;
    				}else {
    					if(checkInterval(startInterval, endInterval)) {
    						rerult.add(startInterval);
    						startInterval = endInterval;
    					}else {
    						startInterval.end = endInterval.end;
    					}
    				}
    			}
    		}
        }
        return rerult;
    }

	private boolean checkInterval(Interval start,Interval end) {
		return start.end < end.start;
	}
}

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}
	