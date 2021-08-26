/*
- https://leetcode.com/problems/interval-list-intersections/
- simple validation of finding intersection using two pointers
- check values min and max and store
*/
class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if(firstList.length == 0 || secondList.length == 0) {
            return (new int[0][0]);
        }
        
        int p1 = 0;
        int p2 = 0;
        List<int[]> res = new ArrayList();
        while(p1<firstList.length && p2<secondList.length) {
            int[] interval1 = firstList[p1];
            int[] interval2 = secondList[p2];
            int lo = Math.max(interval1[0], interval2[0]);
            int hi = Math.min(interval1[1], interval2[1]);
            if(lo <= hi) {
                res.add(new int[]{lo,hi});
            }
            if(interval2[1]<interval1[1]) {
                p2++;
            } else {
                p1++;
            }
        }
        
        int[][] arr = new int[res.size()][2];
        arr = res.toArray(arr);
        return arr;
    }
}
