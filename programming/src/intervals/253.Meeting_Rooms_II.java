/*
https://leetcode.com/problems/meeting-rooms-ii/submissions/
- sort with start time
- use min heap to store end time of meeting
- if end time of top of min heap is less than start time of this new meeting than use this room i.e. remove from pq and add new end time of this meeting
- otw add end time of this meeting anyway to increase pq size and allocate another meeting room
*/
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int len = intervals.length;
        if(len == 0) {
            return 0;
        }
        if(len == 1) {
            return 1;
        }
        Arrays.sort(intervals,(int[] a, int[] b)->(a[0]-b[0]));
        PriorityQueue<Integer> pq = new PriorityQueue();
        //add end of first meeting time in pq
        int[] firstInterval = intervals[0];
        pq.add(firstInterval[1]);
        for(int i=1;i<len;i++) {
            int[] interval = intervals[i];
            
            //at top of pq will be minimum end time of all meetings
            int top = pq.peek();
            if(interval[0]>=top) {
                //remove top as this meeting room is free now
                pq.poll();
            }
            //add interval meeting room to priority queue independent of either it use existing room (if removed above) or new room
            pq.offer(interval[1]);
        }
        return pq.size();
    }
}
