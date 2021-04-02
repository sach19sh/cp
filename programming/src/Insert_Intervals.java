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
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals.length < 1) {
            int[][] res = new int[][]{{newInterval[0],newInterval[1]}};
            return res;
        }
        
        int[][] newarray = new int[intervals.length+1][intervals[0].length];
        int i = 0;
        for(int[] interval : intervals) {
            newarray[i] = interval;
            i++;
        }
        newarray[i] = newInterval;
        
        Arrays.sort(newarray, (i1,i2) -> Integer.compare(i1[0], i2[0]));
        int[] currentInterval = newarray[0];
        List<int[]> res = new ArrayList<>();
        res.add(currentInterval);
        for(int[] interval : newarray) {
            if(interval[0]<=currentInterval[1]) {
                currentInterval[1] = Math.max(interval[1], currentInterval[1]);
            } else {
                currentInterval = interval;
                res.add(currentInterval);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}