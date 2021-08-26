//https://leetcode.com/problems/non-overlapping-intervals/
// Three cases to identify 
// 1. if no overlap move pointer of left and right without increasing count
// 2. if overlap such that some part of right is overlapping , count right as deleted, move right pointer
// 3. if complete overlap delete left or count left as deleted
// 4. return count

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length<=1) {
            return 0;
        }
        
        //sort the given intervals
        Arrays.sort(intervals, (i1,i2)-> Integer.compare(i1[0], i2[0]));
        int n = intervals.length;
        //use two pointers
        int l = 0;
        int r = 1;
        int count = 0;//to count removal of overlapping intervals
        while(r<n) {
            //case-1 non overlapping intervals
            if(intervals[l][1] <= intervals[r][0]) {
                l = r;
                r = r+1;
            }
            
            //case-2 overlapping when right one is partially inside left
            else if(intervals[l][1] <= intervals[r][1]) {
                count++;
                r=r+1; //counting right as deleted
            }
            
            //case 3 overlapping when left is completely overlapping right
            else if(intervals[l][1] > intervals[r][1]) {
                count++;
                l=r;//counting left as deleted
                r=r+1; 
            }
        }
        
        return count;
    }
}
