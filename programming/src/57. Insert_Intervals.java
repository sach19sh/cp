/**57 Insert Intervals
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

 

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
Example 3:

Input: intervals = [], newInterval = [5,7]
Output: [[5,7]]
Example 4:

Input: intervals = [[1,5]], newInterval = [2,3]
Output: [[1,5]]
Example 5:

Input: intervals = [[1,5]], newInterval = [2,7]
Output: [[1,7]]
 

Constraints:

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= intervals[i][0] <= intervals[i][1] <= 105
intervals is sorted by intervals[i][0] in ascending order.
newInterval.length == 2
0 <= newInterval[0] <= newInterval[1] <= 105**/

class Solution {
    public int[][] insert(int[][] intervals, int[] newInt) {
        
        if(intervals.length < 1){
            int[][] newinterval = new int[1][2];
            newinterval[0] = newInt;
            return newinterval;
        }
        int[][] newinterval = new int[intervals.length+1][intervals[0].length];
        for(int i=0;i<intervals.length;i++) {
            newinterval[i] = intervals[i];
        }
        newinterval[intervals.length] = newInt;
        
        //Sort array
        Arrays.sort(newinterval, (a,b)->(a[0]-b[0]));
        
        int[] current = newinterval[0];
        int count = 0;
        List<int[]> result = new ArrayList<>();
        result.add(current);
        
        //merge
        for(int[] input : newinterval) {
            if(count == 0) {
                count++;
                continue;
            }
            int start = input[0];
            int end = input[1];
            if(start <= current[1]) {
                current[1] = Math.max(end, current[1]);
            } else {
                current = input;
                result.add(current);
            }
            
        }
        return result.toArray(new int[result.size()][]);
    }
}