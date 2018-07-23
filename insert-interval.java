// https://leetcode.com/problems/insert-interval/description/
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    // Modify input list
    // Time : O(n), Space: O(n)
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int i=0;
        while(i<intervals.size() && intervals.get(i).end < newInterval.start){
            i++;
        }
        while(i<intervals.size() && newInterval.end >= intervals.get(i).start){
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            intervals.remove(i);
        }
        intervals.add(i, newInterval);
        return intervals;
    }
    // Make new output list
    // Time: O(n), Space: O(n)
    public List<Interval> insert2(List<Interval> intervals, Interval newInterval) {
        List<Interval> rList = new ArrayList<Interval>();
        boolean isDone = false;
        for(int i=0; i<intervals.size(); i++){
            Interval curInterval = intervals.get(i);
            if(isDone || curInterval.end < newInterval.start) rList.add(curInterval);
            else if(newInterval.end < curInterval.start){
                isDone = true;
                rList.add(newInterval);
                rList.add(curInterval);
            }else{
                newInterval.start = Math.min(newInterval.start, curInterval.start);
                newInterval.end = Math.max(newInterval.end, curInterval.end);
            }
        }
        if(!isDone) rList.add(newInterval);
        return rList;
    }
}
